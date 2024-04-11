package concurrent;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsumerProducer {

    public static List<Integer> buff = new ArrayList<>();

    public static class Producer extends Thread {
        public Producer() {
            super("Producer");
        }
        @Override
        public void run() {
            Random rnd = new Random();
            while (true) {
                int n = rnd.nextInt();
                synchronized (buff) {   // lock
                    buff.add(n);
                    buff.notify();
                    System.out.printf("%s: added %d\n", getName(), n);
                }                       // unlock
            }
        }
    }
    public static class Consumer extends Thread {
        public Consumer() {
            super("Consumer");
        }
        @Override
        public void run() {
            while (true) {
                synchronized (buff) {
                    if (buff.isEmpty()) {
                        try {
                            buff.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int n = buff.remove(0);
                    System.out.printf("%s: removed %d\n", getName(), n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread p = new Producer();
        Thread c = new Consumer();
        p.start();
        c.start();

        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {}
    }
}

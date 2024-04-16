package concurrent;


import java.util.*;

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
                } // unlock
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
                synchronized (buff) {  // lock
                    if (buff.isEmpty()) {
                        try {
                            buff.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int n = buff.remove(0);
                    System.out.printf("%s: removed %d\n", getName(), n);
                } // unlock
            }
        }
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            Thread t = new Producer();
            t.start();
            threads.add(t);
            t = new Consumer();
            t.start();
            threads.add(t);
        }
        try {
            threads.get(0).join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

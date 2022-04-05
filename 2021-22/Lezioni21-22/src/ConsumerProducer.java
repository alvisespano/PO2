import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsumerProducer {

    public static Random rand = new Random();

    public static List<Integer> q = new ArrayList<>();

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(rand.nextLong(100L));
                    synchronized (q) {
                        q.add(rand.nextInt(100));
                        q.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (q) {
                    if (q.isEmpty()) {
                        try {
                            q.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int n = q.remove(0);
                    System.out.printf("consumed: %d (size = %d)\n", n, q.size());
                }
            }
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();
        p.start();
        c.start();

        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {}
    }

}

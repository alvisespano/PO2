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

                    synchronized (q) {  // LOCK
                        q.add(rand.nextInt(100));
                        q.notify();
                    }  // UNLOCK
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
                synchronized (q) { // LOCK
                    if (q.isEmpty()) {
                        try {
                            q.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int n = q.remove(0);
                    System.out.printf("consumed: %d (size = %d)\n", n, q.size());
                } // UNLOCK
            }
        }
    }

    public static void main(String[] args) {
        Producer p1 = new Producer();
        Producer p2 = new Producer();
        Consumer c1 = new Consumer();
        Consumer c2 = new Consumer();
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        try {
            p1.join();
        } catch (InterruptedException e) {}
    }

}

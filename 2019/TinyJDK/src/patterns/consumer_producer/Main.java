package patterns.consumer_producer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private static BlockingQueue<String> q = new LinkedBlockingQueue<>();
    private static Random rand = new Random();

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(rand.nextInt(500));

                    String s = q.take();
                    System.out.println("consumer: " + s);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(rand.nextInt(500));

                    int len = rand.nextInt(10);
                    String s = "";
                    for (int i = 0; i < len; ++i) {
                        s = s + String.format("%d", i); // TODO: usare StringBuilder come suggerito da IntelliJ
                    }
                    System.out.println("producer: " + s);
                    q.add(s);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }

}

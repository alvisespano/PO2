package patterns.consumer_producer;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private static BlockingQueue<String> q = new LinkedBlockingQueue<>();
    private static Random rand = new Random();

    public static void print(String s) {
        Thread t = Thread.currentThread();
        System.out.println(String.format("[%s:%d] %s", t.getName(), t.getId(), s));
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(rand.nextInt(500));

                    String s = q.take();
                    print(s);

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
                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < len; ++i) {
                        s.append(String.format("%d", i));
/*                        int n = rand.nextInt(26);
                        int m = Character.getNumericValue('a') + n;
                        char c = Character.toChars(m)[0];
                        s = s + c;
                         */
                    }
                    print(s.toString());
                    q.add(s.toString());

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

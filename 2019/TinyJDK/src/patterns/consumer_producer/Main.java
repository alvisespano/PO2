package patterns.consumer_producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    //private static BlockingQueue<String> q = new LinkedBlockingQueue<>();
    private static List<String> q = new ArrayList<>();
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String s;
                int size;
                synchronized (q) {
                    if (q.isEmpty()) {
                        try {
                            q.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    s = q.remove(0);
                    size = q.size();
                }
                print(String.format("%s (size = %d)", s, size));
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
                        //s.append(String.format("%d", i));
                        int n = rand.nextInt(26);
                        int m = Character.getNumericValue('a') + n + 87;
                        char c = Character.toChars(m)[0];
                        s.append(c);

                    }
                    print(s.toString());
                    synchronized (q) {
                        q.add(s.toString());
                        q.notify();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            Producer p = new Producer();
            p.setName(String.format("producer-%d", i));
            p.start();

            Consumer c = new Consumer();
            c.setName(String.format("consumer-%d", i));
            c.start();
        }


        print("byebye");
    }

}

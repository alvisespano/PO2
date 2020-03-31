package it.unive.dais.po2.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsumerProducer {

    private static Random rnd = new Random();

    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    private static void log(String msg) {
        Thread self = Thread.currentThread();
        System.out.println(String.format("%s[%d]: %s", self.getName(), self.getId(), msg));
    }

    private static Object dummy = new Object();

    public static class Consumer extends Thread {
        private List<Integer> l1;
        private List<Integer> l2;

        public Consumer(List<Integer> l1, List<Integer> l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (dummy) {
                    if (!l1.isEmpty() && !l2.isEmpty()) {
                        int n1 = l1.remove(0);
                        int n2 = l2.remove(0);
                        log(String.format("Consumer: pop: %d, %d (size: %d, %d) %s %s", n1, n2, l1.size(), l2.size(), l1, l2));
                    }
                }
                try {
                    Thread.sleep(rand(1, 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        private List<Integer> l1;
        private List<Integer> l2;

        public Producer(List<Integer> l1, List<Integer> l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        private int counter = 0;

        @Override
        public void run() {
            while (true) {
                int n = counter++;
                synchronized (dummy) {
                    l1.add(n);
                    l2.add(n);
                    log(String.format("Producer: push: %d (size: %d, %d) %s %s", n, l1.size(), l2.size(), l1, l2));
                }
                try {
                    Thread.sleep(rand(1, 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();
            Consumer c = new Consumer(l1, l2);
            Producer p1 = new Producer(l1, l2);
            Producer p2 = new Producer(l1, l2);
            c.start();
            p1.start();
            p2.start();

            c.join();
            p1.join();
            p2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

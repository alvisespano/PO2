package it.unive.dais.po2.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsumerProducer {

    private static List<Integer> l = new ArrayList<>();

    public static class Producer extends Thread {
        @Override
        public void run() {
            Random rnd = new Random();
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int n = rnd.nextInt(100);
                synchronized (l) {
                    l.add(n);
                    System.out.printf("produced %d\n", n);
                }
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (l) {
                    if (!l.isEmpty()) {
                        int n = l.remove(0);
                        System.out.printf("consumed %d\n", n);
                    }
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

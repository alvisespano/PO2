package it.unive.dais.po2.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class ConsumerProducer {

    private static List<Integer> l = new ArrayList<>();

    public static class Producer extends Thread {
        @Override
        public void run() {
            Random rnd = new Random();
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int n = rnd.nextInt(100);
                synchronized (l) {
                    l.add(n);
                    l.notify();
                    System.out.printf("#%d: produced %d\n", Thread.currentThread().getId(), n);
                }
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (l) {
                    if (l.isEmpty()) {
                        try {
                            l.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    int n = l.remove(0);
                    System.out.printf("#%d: consumed %d\n", Thread.currentThread().getId(), n);
                }
            }
        }
    }

    private static
    void populate(int n, List<Thread> p, Supplier<? extends Thread> f) {
        for (int i = 0; i < n; ++i) {
            Thread t = f.get();
            p.add(t);
            t.start();
        }

    }

    public static void main(String[] args) {
        List<Thread> p = new ArrayList<>();
        Supplier<Producer> u = Producer::new;
        populate(10, p, u);
        populate(20, p, new Supplier<>() {
            @Override
            public Thread get() {
                return new Consumer();
            }
        });

        for (Thread t : p) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}

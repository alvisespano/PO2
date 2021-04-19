package it.unive.dais.po2.aa2020_21.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsumerProducer {

    private static final BlockingQueue<Integer> l = new LinkedBlockingQueue<>();

    public static class Consumer extends Thread {

        @Override
        public void run() {
            System.out.println("consumer started");
            while (true) {
                try {
                    int n = l.take();
                    System.out.printf("consumer: %d\n", n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            System.out.println("producer started");
            Random rnd = new Random();
            while (true) {
                l.add(rnd.nextInt() % 100);
            }
        }
    }

    public static void main(String[] args) {
        try {

            Thread c1 = new Consumer();
            Thread p1 = new Producer();

            c1.start();
            p1.start();

            c1.join();
            p1.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

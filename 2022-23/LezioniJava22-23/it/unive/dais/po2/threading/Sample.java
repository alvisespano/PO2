package it.unive.dais.po2.threading;

import java.util.Random;

public class Sample {

    private static Random rnd = new Random();

    Object myMutex = new Object();

    private static int rand(int a, int b) {
        synchronized (rnd) {
            return rnd.nextInt(b - a) + a;
        }
    }

    private static void loop() {
        while (true) {
            synchronized (rnd) {
                try {
                    Thread.sleep(rand(200, 1000));
                } catch (InterruptedException e) {}

                System.out.println("ciao sono ");
                System.out.println("il thread #"
                        + Thread.currentThread().getId());
            }
        }

    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            loop();
        }

    }

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        t1.start();

        
        new Thread(Sample::loop).start();
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
        loop();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

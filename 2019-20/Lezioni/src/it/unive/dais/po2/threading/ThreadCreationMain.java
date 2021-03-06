package it.unive.dais.po2.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ThreadCreationMain {


    private static void count(String id, long millis, int times) {
        try {
            for (int i = 0; i < times; ++i) {
                System.out.println(id + ": " + i);
                Thread.sleep(millis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class CounterThread extends Thread {
        private String name;
        private long millis;
        private int times;

        public CounterThread(String name, long millis, int times) {
            this.name = name;
            this.millis = millis;
            this.times = times;
        }

        @Override
        public void run() {
            count(name, millis, times);
        }
    }

    private static Random rnd = new Random();

    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    public static void main(String[] args) {

        Collection<Thread> threads = new ArrayList<>();
        for (int i = 0; i < rand(5, 20); ++i) {
            final String name = String.format("thread#%d", i);
            final long millis = rand(200, 800);
            final int times = rand(10, 30);

            // creazione di un thread passando un Runnable al costruttore
            // Runnable = lambda senza argomenti e senza ritorno
            Thread t1 = new Thread(() -> {
                count(name, millis, times);
            });
            // stessa cosa ma con la lambda dezuccherata in un Runnable
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    count(name, millis, times);
                }
            });
            // creazione di un thread tramite una anonymous class con override al volo del metodo run
            Thread t3 = new Thread() {
                @Override
                public void run() {
                    count(name, millis, times);
                }
            };
            // creazione di una istanza di CounterThread, cioè di un sottotipo di Thread
            // e passaggio dei parametri al costruttore
            Thread t4 = new CounterThread(name, millis, times);

            Thread t = t1;
            t.start();
            threads.add(t);
        }

        // attendo che tutti i thread creati finiscano
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

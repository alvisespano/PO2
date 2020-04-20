package it.unive.dais.po2.other;

import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;

public class FilosofiACena__Francesco {

    public static class Monitor {
        @Nullable
        private static Monitor instance = null;

        private int postiTot;
        private boolean[] bacchetta;

        private Monitor(int postiTot) {
            this.postiTot = postiTot;
            this.bacchetta = new boolean[postiTot];
            for (int i = 0; i < postiTot; ++i)
                bacchetta[i] = true;
        }

        public synchronized void raccogli_sx(int posto) throws InterruptedException {
            while (!bacchetta[posto])
                this.wait();
            bacchetta[posto] = false;
        }

        public synchronized void raccogli_dx(int posto) throws InterruptedException {
            raccogli_sx((posto + 1) % postiTot);
        }

        public synchronized void deposita_sx(int posto) {

            bacchetta[posto] = true;
        }

        public synchronized void deposita_dx(int posto) {
            deposita_sx((posto + 1) % postiTot);
            this.notifyAll();
        }

        public static synchronized Monitor getInstance(int postiTot) {
            if (instance == null)
                instance = new Monitor(postiTot);
            return instance;
        }
    }

    public static void codiceFilosofo(int posto, Monitor tavola) throws InterruptedException {
        while (true) {
            /*PENSA*/
            System.out.println("Filosofo " + posto + " pensa");
            Thread.sleep(1000);
            tavola.raccogli_sx(posto);/*raccoglie la bacchetta sinistra*/
            tavola.raccogli_dx(posto);/*raccoglie la bacchetta destra*/
            /*MANGIA*/
            System.out.println("Filosofo " + posto + " mangia");
            Thread.sleep(1000);
            tavola.deposita_sx(posto);/*deposita la bacchetta sinistra*/
            tavola.deposita_dx(posto);/*deposita la bacchetta destra*/
        }
    }

    public static class Filosofo extends Thread implements Runnable {
        private int posto;
        private Monitor tavola;

        public Filosofo(int posto, Monitor tavola) {
            this.posto = posto;
            this.tavola = tavola;
        }

        @Override
        public void run() {
            try {
                codiceFilosofo(posto, tavola);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int nFilosofi = 5;
        final Monitor tavola = Monitor.getInstance(5);
        Collection<Thread> filosofi = new ArrayList<>();
        for (int i = 0; i < nFilosofi; ++i) {
            //  Filosofo filosofo = new Filosofo(i, tavola);

            final int[] posto = new int[] { i };    // trucco dell'array final con 1 solo elemento
            // final int posto = i;                 // trucco built-in

            Thread filosofo = new Thread(() -> {
                try {
                    codiceFilosofo(posto[0], tavola);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            filosofo.start();
            filosofi.add(filosofo);
        }
    }
}



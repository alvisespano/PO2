package it.unive.dais.po2.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ThreadCreationMain {

    /**
     * Metodo count
     * @param id        ID di chi parla (String)
     * @param millis    Tempo di sleep
     * @param times     Volte che deve essere ripetuto il codice
     */

    private static void count(String id, long millis, int times) {
        try {
            for (int i = 0; i < times; ++i) {
                System.out.println(id + ": " + i);
                Thread.sleep(millis); // SLEEP
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * THREAD
     * Per costruire un thread si crea una classe che estende la classe Thread,
     * dove all'interno si fa l'Override del metodo run.
     *
     * La classe thread è una classe astratta che ha il metodo run astratto,
     * che si eredita e deve essere implementato.
     */
    public static class CounterThread extends Thread {
        private String name;
        private long millis;
        private int times;


        /**
         * Costruttore del thread con i seguenti parametri
         * che in questo caso li copio come se fossero dei campi
         * @param name      ID di chi parla (String)
         * @param millis    Tempo di sleep
         * @param times     Volte che deve essere ripetuto il codice
         */
        public CounterThread(String name, long millis, int times) {
            this.name = name;
            this.millis = millis;
            this.times = times;
        }

        //Dentro la run c'è il codice del thread da eseguire
        @Override
        public void run() {
            count(name, millis, times);
        }
    }


    /**
     * Funzione che crea un oggetto Random
     */
    private static Random rnd = new Random();

    /**
     * Metodo rand che genera un numero random dato:
     * @param a intero di inizio intervallo incluso
     * @param b intero di fine interfallo escluso (per includerlo faccio +1)
     * @return  numero random
     */
    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    public static void main(String[] args) {

        // Collection usata per contenere i Thread
        Collection<Thread> threads = new ArrayList<>();

        // Generatore di minimo 5 e massimo 20 thread
        for (int i = 0; i < rand(5, 20); ++i) {
            // Binding: evito di replicare la chiamata a rand e il name
            final String name = String.format("thread#%d", i);
            final long millis = rand(200, 800);
            final int times = rand(10, 30);


            /* creazione di un thread passando un Runnable al costruttore
             * Runnable = lambda senza parametri e senza ritorno;
             *            interfaccia che non prende niente e non ritorna niente.
             *
             * Questa sintassi è l'invocazione di un costruttore della classe Thread.
             * Sintassi della costruzione degli oggetti:
             *  - new
             *  - NomeClasse
             *  - argomenti per il costruttore (che in questo caso è una Runnable)
             *  - );
             */
            Thread t1 = new Thread(() -> {
                count(name, millis, times);
            });


            /* stessa cosa ma con la lambda dezuccherata in un Runnable
             *
             * In questo caso l'anonymus class parte da new Runnable() { e finisce alla prima }
             */
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    count(name, millis, times);
                }
            });


            /* creazione di un thread tramite una anonymous class con override al volo del metodo run
             *
             * Questa è sintassi della classe anonima.
             * Sintassi dell'anonymus class: new NomeClasse() { Override del metodo }
             */
            Thread t3 = new Thread() {
                @Override
                public void run() {
                    count(name, millis, times);
                }
            };


            // creazione di una istanza di CounterThread, cioè di un sottotipo di Thread
            // e passaggio dei parametri al costruttore
            Thread t4 = new CounterThread(name, millis, times);



            // rinnomino il Thread
            Thread t = t1;

            // il mainThread sta startando altri thread
            t.start();  // il metodo start() serve a far spawnare il thread
                        // TO SPAWN, in SO, significa: creare un thread


            // aggiungo i thread alla Collection thread
            threads.add(t);
        }

        // attendo che tutti i thread creati finiscano
        for (Thread t : threads) {
            try {
                /*
                 * Ogni processo può avere più thread (fili) in esecuzione
                 *
                 * Join significa unire, quindi se voglio aspettare che uno di questi
                 * fili finisca, lo unisco al mio filo (programma principale).
                 *
                 * Wait: è il metodo che hanno tutti gli object, e serve ad aspettare
                 * la notifica del monitor (commissional mutex),
                 * quindi wait è un nome già occupato
                 */
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



/*
 * Lezione 8
 *
 * CREAZIONE DI UN THREAD PASSANDO UN Runnable AL COSTRUTTORE
 * Runnable = lambda senza argomenti e senza ritorno
 * Lamda da void a void ha tipo Function<Void, Void> ma, siccome dà errore,
 * è stato implementata l'interfaccia Runnable che è
 * l'interfaccia delle funzioni che prendono niente e restituiscono niente,
 * quindi void -> void
 *
 * IMPLEMENTAZIONE DI RUNNABLE
 * public interface Runnable {
 *     void run();
 * }
 *
 * COMPATIBILITA' DI Runnable
 * Runnable è compatibile solo con le funzioni lambda
 * che prendono niente e non ritornano niente.
 *
 * AVVIO DI UN THREAD (differenza tra start() e run())
 * Per avviare un thread chiamo il metodo start() e non run()
 * perché run() contiene il codice che deve eseguire il thread,
 * invece start() avvia il thread.
 *
 * Grazie alla sintassi delle anonymus class si può costruire un oggetto di una
 * sottoclasse che non ho neanche costruito
 *      Thread t = new Thread() {
 *          @Override
 *          public void run() {
 *              count(name, millis, times);
 *          }
 *      }
 *
 * COLLECTION DI THREAD
 * Sono stati messi tutti i thread creati in una Collection per un motivo di coding:
 * come faccio a sapere quando finiscono i thread se non so quanti sono,
 * perché sono un numero casuale?
 * Quando creo i thread, lo metto in una ArrayList, dopo di ché scorro l'ArrayList e
 * li joino tutti quanti.
 *
 *
 * ANONYMUS CLASS
 * L'anonymus class è l'istanziazione di una sottoclasse di quella che si sta scrivendo,
 * solo che non viene dato il nome della sottoclasse, ma si crea immediatamente l'oggetto.
 */

package it.unive.dais.po2.threading;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 *
 */
public class ConsumerProducer {

    // Generatore di numeri Random
    private static Random rnd = new Random();

    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    // se fosse necessario synchronized
    private static synchronized int rand__sync(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    // se fosse necessario synchronized: dezuccherata
    private static synchronized int rand__sync_desugared(int a, int b) {
        synchronized (ConsumerProducer.class) {
            return rnd.nextInt(b - a + 1) + a;
        }
    }

    // se fosse necessario usare i lock
    private static int rand__lock(int a, int b) {
        ReentrantLock l = new ReentrantLock();
        try {
            return rnd.nextInt(b - a + 1) + a;
        } finally {
            l.unlock();
        }
    }

    /**
     * Funzione che fa in modo che un thread quando scrive in System.out.println,
     * scrive anche come si chiama.
     * Funzione usata da entrambi i thread, chiamata LOG che serve a loggare una linea,
     * viene passato un messaggio
     * @param msg   messaggio che si vuole stampare
     */
    private static void log(String msg) {
        /*
         * currentThread()
         * è un metodo statico della classe thread,
         * ritorna l'oggetto thread che rappresneta il thread corrente,
         * ovvero il thread che sta eseguendo la chiamata in quel momento.
        */
        Thread self = Thread.currentThread();
        System.out.println(String.format("%s[%d]: %s", self.getName(), self.getId(), msg));
    }

    /**
     * Siccome sto arbitrando l'accesso all'ArrayList, uso lo stesso Object come mutex,
     * lo uso su entrambe le synchronized.
     */
    private static Object dummy = new Object();

    /**
     *  CONSUMATORE
     */
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
                /*
                 * SYNCHRONIZED
                 * synchronized(Object espressione_mutex) { sezione critica }
                 */
                synchronized (dummy) {
                    // dummy.lock();    // funzionamento di synchronized

                    // Controllo che ci sia almeno un elemento nella lista (l1 e l2)
                    if (!l1.isEmpty() && !l2.isEmpty()) {
                        /*
                         * REMOVE
                         * Le liste hanno una remove posizionale che dato l'indice
                         * estrae un elemento di tipo T (generic type) e lo rimuove.
                         * (Equivalente di fare una pop: prende un elemento dalla testa e lo rimuove)
                         */
                        int n1 = l1.remove(0);
                        int n2 = l2.remove(0);

                        // messaggio da passare a log del consumatore
                        log(String.format("Consumer: pop: %d, %d (size: %d, %d) %s %s", n1, n2, l1.size(), l2.size(), l1, l2));

                        // dummy.unlock();  // funzionamento di synchronized
                    }
                }
                // controllo se la sleep va a buon fine
                try {
                    // sleep altrimenti il consumer consuma troppo velocemente gli elementi prodotti
                    // campiono l'ArrayList ogni rand(1,50) secondi
                    Thread.sleep(rand(1, 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * PRODUTTORE
     */
    public static class Producer extends Thread {
        private List<Integer> l1;
        private List<Integer> l2;

        public Producer(List<Integer> l1, List<Integer> l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        // contatore per fare dei numeri sequenziali
        private int counter = 0;

        @Override
        public void run() {
            /*
             * crea un numero Random che però rimane sempre costante
             * long ms = rand(10, 50);
             */
            while (true) {
                int n = counter++;
                synchronized (dummy) {
                    // dummy.lock();    // funzionamento di synchronized

                    // Producer pusha lo stesso numero in entrambe le liste
                    l1.add(n);
                    l2.add(n);

                    // messaggio da passare a log del produttore
                    log(String.format("Producer: push: %d (size: %d, %d) %s %s", n, l1.size(), l2.size(), l1, l2));

                    // dummy.unlock();  // funzionamento di synchronized
                }
                // controllo se la sleep va a buon fine
                try {
                    // il rand crea delle attese sempre diverse di rand(1,50) secondi
                    Thread.sleep(rand(1, 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * MAIN
     * @param args  standard arguments
     */
    public static void main(String[] args) {
        try {
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();
            Consumer c = new Consumer(l1, l2);  // creo consumatore
            Producer p1 = new Producer(l1, l2); // creo produttore 1
            Producer p2 = new Producer(l1, l2); // creo produttore 2

            // START
            c.start();  // inizia il thread consumatore
            p1.start(); // inizia il thread produttore
            p2.start(); // inizia il thread produttore

            // JOIN
            c.join();   // il main thread fa aspettare che il thread c finisca
            p1.join();  // il main thread fa aspettare che il thread p1 finisca
            p2.join();  // il main thread fa aspettare che il thread p2 finisca

        } catch (InterruptedException e) { // qualsiasi cosa succeda il programma viene interrotto
            e.printStackTrace();
        }
    }

}

/*
 * String.format piuttosto della + per concatenare le stringhe:
 *  - più elegante: prima si scrive il formato di quello che si vuole scrivere con i %...,
 *    poi si passano i parametri di quello che si vuole sostituire ai %...
 *  - più efficiente.
 *
 * _____________________________________________________________________________________________
 *
 * SINCRONIZZAZIONE
 * La sincronizzazione avviene attraverso i mutex, che sono dei semafori che
 * servono a mettere in fermo uno, in modo che un altro faccia un pezzo di codice che vuole
 * sia garantito che lo faccia tutto e non vuole essere interrotto, l'altro aspetta sul semaforo
 * e quando è verde e fa lui il suo pezzo di codice e non vuole essere interrotto da nessuno.
 * Il pezzo di codice che nessuno vuole venga interrotto si chiama sezione critica, e il fatto di
 * eseguirlo senza essere interrotto si dice che si fa un'operazione atomica.
 * Rendere atomica una certa serie di operazioni significa fare in mdoo che una certa sequenza
 * di operazioni non sia interrompibile
 *
 * In Java, tutti gli oggetti hanno le primitive di sincronizzazione che provengono da Object,
 * ereditate automaticamente.
 * Quando si scrive una classe, si estende sempre Object e si ereditano sempre una certa quantità
 * di metodi: equals, toString, hashCode, wait e notify. Queste ultime due primitive (wait e notify)
 * sono due primitive per aspettare che il semaforo diventi verde, e wait che serve per mettere
 * il semaforo a rosso.
 *
 * LOCK di un mutex
 * Il lock di un mutex fa 2 cose:
 *  - se il semaforo è rosso, aspetta che il semaforo sia verde (UNLOCK)
 *  - se il semaforo è verde, lo mette direttamente a rosso (LOCK).
 *
 * SYNCHRONIZED (è una keyword, non un metodo) funzionamento
 *  - synchronized
 *  - (espressione di tipo Object almeno)
 *  - quando entra nel blocco chiama la LOCK sull'oggetto messo tra tonde
 *  - quando esce dal blocco chiama la UNLOCK dell'oggetto messo tra tonde
 *
 * Scrivere syncrhonized su un metodo (private static synchronize int nomeMetodo(parametri))
 * è equivalente a scrivere
 *      private static synchronize int nomeMetodo(parametri) {
 *          synchronize(this) {...}
 *      }
 * =============================================================================================
 *
 * RACE CONDITION
 * Una race condition è quando 2 thread accedono alla stessa zona di memoria (dati) e
 * li modificano uno sotto il naso dell'altro lasicando in uno stato di preemption i dati
 * corrotti.
 *
 */

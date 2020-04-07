package it.unive.dais.po2.threading;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;    // usata per lock
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * CONSUMATORE-PRODUTTORE
 */
public class ConsumerProducer {

    // Generatore di numeri Random
    private static Random rnd = new Random();

    /**
     * RAND: metodo privato, usato per generare numeri casuali entro un dato intervallo
     * @param a integer
     * @param b integer
     * @return  integer
     */
    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
        /*
         * nextInt():   se due thread chiamano contemporaneamente questo metodo?
         *              Questo metodo ti dà un numero casuale tra 0 e il bound,
         *              ed è un metodo offerto dalla classe Random del JDK.
         *              Il metodo nextInt cambia lo stato dell'oggetto (rnd)?
         *              La chiamata a nextInt altera lo stato dell'oggetto (rnd),
         *              ma se lo si chiama contemporaneamente, può corrompere gli stati.
         *              In questa funzione serve il synchronized in modo tale che
         *              questa funzione venga chiamata in maniera atomica.
         *              Facendo così garantiamo che l'oggetto rnd cambia stato
         *              a una richiesta alla volta, le richieste si mettono in fila e
         *              non ce ne sono mai di contemporanee.
         *
         *              La seconda strada è andare a leggere nella documentazione se
         *              il metodo nextInt è già synchronized.
         *              Keyword di ricerca se un metodo è synchronized:
         *               - threadsafe: quando una funzione è thread safe vuol dire che
         *                             è una funzione che è sicura per i thread, cioè
         *                             è pensata apposta per essere chiamata in multithreading
         *                             senza doverla sincronizzare perché il lavoro lo fa lei
         *              Essere threadsafe è una bella cosa, così tanti thread possono chiamare
         *              le stesse cose senza pestarsi i piedi, non serve il synchronized, ma
         *              per essere threadsafe sacrifica un po' la performace, perché:
         *               - un thread ne deve attendere un altro che metta il semaforo verde,
         *                 quindi finisca il suo job (si crea un imbuto);
         *               - anche se sei a singolo thread, e si usa la classe Random (pensata
         *                 multithread, quindi con i mutex dentro), comunque metto a rosso e
         *                 verde un semaforo anche se non ce n'è bisogno, introducendo una
         *                 lentezza implicita.
         */
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

    /**
     * Metodo lock
     * @param a
     * @param b
     * @return
     */
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

        Thread self = Thread.currentThread(); // Fa solo una lettura dell'oggetto thread.
        System.out.println(String.format("%s[%d]: %s", self.getName(), self.getId(), msg));
        /* Seconda riga della funzione log:
         * System.out.println: dato un argomento di tipo string, lo stampa in stdOut
         *                     Stampare in stdOut è un'operazione di modifica dello stato dello stdOut,
         *                     quindi se due thread dovessero, per caso, fare la println nello stesso
         *                     momento, quale dei due scrive per primo?
         *                     La buona notizia è che la println è synchronized, cioè quando stampa qualcosa
         *                     mette a rosso il semaforo che nessuno stampi nulla su quel canale in quel
         *                     momento.
         *                     Quindi non è necessario aggiungere un synchronized ulteriore alla funzione log.
         * String.format:   data una stringa e un numero variabile di argomenti,
         *                  renderizza la stringa finale, sostituendo ai placeholder le varie parti
         *                  Operazione che non modifica nulla ma crea una nuova stringa,
         *                  quindi se più thread la eseguono contemporaneamente,
         *                  ognuno ha la sua nuova stringa.
         * getName(): getter
         * getId():   getter
         * msg:       String
        */
    }

    /**
     * Siccome sto arbitrando l'accesso all'ArrayList, uso lo stesso Object come mutex,
     * lo uso su entrambe le synchronized.
     */
    private static Object dummy = new Object();

    /**
     *  CONSUMATORE
     *
     *  Consumare: leggere dei dati e levarli da dove si sono letti.
     */
    public static class Consumer extends Thread {
        private List<Integer> l1;
        private List<Integer> l2;

        public Consumer(List<Integer> l1, List<Integer> l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        /**
         * Metodo run originariamente definito da Thread.
         * Se Thread definisce il metodo run e anche dichiara di implementare Runnable.
         * La classe Thread implementa runnable.
         */
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
 * ____________________________________________________________________________________________________________
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
 * Quando si scrive una classe, si estende sempre Object e si ereditano sempre una certa quantità di metodi:
 *  - equals();
 *  - toString();
 *  - hashCode();
 *  - wait();
 *  - notify().
 * Queste ultime due primitive (wait e notify) sono due primitive per aspettare che
 * il semaforo diventi verde (notify), e impostare il semaforo a rosso (wait).
 *
 * LOCK di un mutex
 * Il lock di un mutex fa 2 cose:
 *  - se il semaforo è rosso, aspetta che il semaforo sia verde (UNLOCK)
 *  - se il semaforo è verde, lo mette direttamente a rosso (LOCK).
 *
 * SYNCHRONIZED
 * synchronized è un costrutto sintattico del linguaggio Java (è una keyword, non un metodo).
 * Il suo statement è:
 *  - synchronized
 *  - (espressione di tipo Object almeno)
 *  - quando entra nel blocco chiama la LOCK sull'oggetto messo tra tonde
 *  - quando esce dal blocco chiama la UNLOCK dell'oggetto messo tra tonde
 *
 * Il costrutto synchronize si dezzucchera in una lock(oggetto) all'inzio del blocco
 * e una unlock(oggetto) alla fine del blocco.
 * L'oggetto non è per forza la struttura dati che si deve sincronizzare, questo è un modo semplice di farlo,
 * ma in casi più generali, quando si possono avere più strutture dati da sincronizzare, allora la scelta va
 * fatta capendo a fondo di cosa si ha bisogno. Si può fare anche un altro oggetto, che non centra niente,
 * che si usa solo come semaforo di un synchronized.
 * Il modo giusto di capire il blocco synchronized è:
 *      il blocco synchronized mi garantisce l'atomicità dell'esecuzione del mio blocco,
 *      usando l'espressione tra tonde () come mutex.
 * Le sue implicazioni sono: se uso tra tonde un oggetto costruito apposta (es. dummy)
 * e lo dedico a questo scopo. Non è che si deve sincronizzare l'oggetto (dummy) ma uso lui come unico mutex.
 * L'importante è che tutti i blocchi di codice che hanno bisogno di arbitrare l'accesso sulla stessa cosa
 * devono usare il medesimo mutex.
 *
 * Scrivere syncrhonized come qualifier nella firma del metodo:
 *      private static synchronize int nomeMetodo(parametri) {...}
 * è equivalente a scrivere, per un metodo STATICO:
 *      private static int nomeMetodo(parametri) {
 *          synchronize(NomeClasse.class) {...}
 *      }
 * oppure per un metodo NON STATICO:
 *      private int nomeMetodo(parametri) {
 *          synchronize(this) {...}
 *      }
 *
 * Il costrutto NomeClasse.class estrae un oggetto a runTime che rappresenta il tipo di una classe, e serve
 * per fare la reflection.
 * NomeClasse.class è un'espressione che ha come tipo:
 *      Class<NomeClasse> cl = NomeClasse.class;
 * Class è una classe che rappresenta l'informazione dei tipi di una classe.
 * Questi oggetti che rappresentano tipi a runTime servono a fare delle cose particolari:
 *  - synchronized di metodi statici.
 *  - reflection: scrivere codice che a runTime manipola pezzi di classi e di metodi del linguaggio.
 *
 * ESEMPIO (inutile, ma solo per far capire il funzionamento del synchronized)
 * private static synchronized int rand(int a, int b) {
 *          return rnd.nextInt(b - a + 1) + a;
 * }
 *
 * private static synchronized void log(String msg) {
 *     Thread self = Thread.currentThread();
 *     System.out.println(String.format("%s[%d]: %s", self.getName(), self.getId(), msg));
 * }
 *
 * Se queste due funzioni fossero entrambe sincronizzate vuol dire che quando chiamo log e quando chiamo rand
 * non può mai accadere che vengano chiamati simultaneamente da due thread, ma vengono eseguiti in modalità
 * atomica, cioè quei due metodi diventano due sezioni critiche, quindi non è più possibile per due thread
 * trovarsi nello stesso istante all'interno di entrambi i blocchi, perché siccome la keyword synchronized
 * si dezzucchera nel blocco synchronized sull'oggetto ConsumerProducer.class e siccome lo fa per entrambi,
 * vuol dire che quel blocco synchronized sta usando lo stesso mutex per sincronizzare sia rand che log,
 * e lo stesso mutex è l'oggetto che rappresenta l'intera classe.
 * Se un thread è entrato dentro log e ha messo il mutex a rosso, esegue il codice di log, poi quando esce
 * mette il semaforo a verde, se mentre lui eseguiva quel codice, un altro thread stava cercando di eseguire
 * o log o rand (o un qualsiasi altro metodo synchronized) allora quell'altro thread si trova di fronte il
 * semaforo rosso.
 *
 * Lo svantaggio della syncronized non permette l'arbitraggio tra multipli lettori e un solo scrittore,
 * se necessario allora uso lock.
 * Il vantaggio è che non è fa deadlock in quanto è un blocco sicuro.
 * ============================================================================================================
 *
 * RACE CONDITION
 * Una race condition è quando 2 thread accedono alla stessa zona di memoria (dati) e
 * li modificano uno sotto il naso dell'altro lasicando in uno stato di preemption i dati
 * corrotti.
 *
 * ____________________________________________________________________________________________________________
 *
 * CODA
 * La coda è una struttura dati FIFO.
 *
 * ____________________________________________________________________________________________________________
 *
 * FIRMA DI UN METODO
 * Qualifier:
 *  - private || public || protected
 *  - [static]
 *  - [synchronized]
 *  - [abstract]
 * Firma del metodo:
 *  - tipo valore di ritorno
 *  - nomeMetodo
 *  - (parametri)
 *  - throws
 * ____________________________________________________________________________________________________________
 *
 * LOCK
 * Metodo lock:
 *  - se è verde mette rosso
 *  - se è rosso aspetto che sia verde e metto rosso.
 * La primitiva lock è l'ingresso dentro la sezione critica (come la wait nei semafori).
 * Lock l = ...;
 * l.lock()
 * try {
 *     // access the resource protected by this lock
 * } finally {
 *     l.unlock();
 * }
 * Il costrutto try è un costrutto a 3 vie:
 *  - try:       esegue il blocco del try,
 *                  - se succede un'eccezione salta nel blocco catch,
 *                  - altrimenti entra nel blocco del finally
 *  - [catch]:   esegue il codice del catch per gestire l'eccezione, e passa al finally
 *               (anche se il try non è stato eseguito completamente)
 *  - [finally]: esegue il codice del finally, che in ogni caso (eccezzione o no) viene eseguito.
 * Se si leva il catch, significa che non viene gestita una certa eccezione, e significa che i metodi
 * potrebbero lanciare un'eccezione, che non viene gestita, quindi perfora lo stack e va al chiamante
 * fino a che qualcuno non lo gestisce. Il blocco del finally fa qualcosa che non si potrebbe scrivere
 * in nessun altro modo, cioè se capita un'eccezione e non viene gestita appositamente dal catch,
 * comunque viene eseguito il blocco del finally, che permette di fare qualcosa sia se succede un'eccezione
 * sia se non succede, sia se succede ma non viene gestita.
 *
 * Quando un mutex si mette a rosso, nell'operazione d'entrata, e succede un'eccezione nel mentre quindi
 * NON continua con la sezione critica che deve essere atomica non salta più fuori e non mette più a verde
 * il mutex, quidi si crea un deadlock, ovvero quando un mutex rimane rosso per sempre e non c'è più modo
 * di sbloccarlo. Il blocco del finally serve proprio per l'operazione di unlock sia che la sezione critica
 * vada a buon fine che non e non crei mai dei deadlock.
 * Questo discorso fa parte del design pattern RESOURCE ALLOCATION (allocazione di risorsa).
 * Una risorsa è qualcosa che si può avere e poi bisogna rilasciare (aprire e chiudere, mettere a rosso e
 * poi a verde). In tutte le operazioni che hanno un'operazione di entrata e poi di uscita che ripristina
 * lo stato iniziale, si chiama resource allocation, e bisogna fare la chiusura sempre, altrimenti la
 * risorsa, se succede un errore durante l'esecuzione del blocco, non viene mai liberata.
 *
 * L'interfaccia lock ha i seguenti metodi:
 *  - lock()
 *  - unlock()
 *  - tryLock(): ritorna un boolean, risponde alla domanda"Posso entrare nella sezione critica? True o false.
 *               Questo metodo dice: "voglio il semaforo solo se è verde, altrimenti no",
 *               ovvero garantisce che entra nella critical section se è verde, altrimenti restituisce false.
 *  - tryLock(long time, TimeUnit unit): è come la tryLock con due argomenti (long time, TimeUnit unit)
 *               e si specifica quanto tempo al massimo si vuole aspettare prima che lei fallisca:
 *                - se è già verde: tutto ok
 *                - se è rosso: la tryLock() dà false e non ti fa entrare,
 *                              invece tryLock(long time, TimeUnit unit) aspetta il tempo passato come parametro
 *                              e poi se lo supera il mutex esce e ritorna false.
 *              I parametri rappresentano il valore e la sua unità di misura
 *  - newCondition()
 *  - lockInterruptibly()
 *
 * Lock è un interfaccia, non una classe.
 *
 * READ LOCK:  se ce ne sono più contemporanei e nessun write lock, non fanno nessun mutex
 * WRITE LOCK: basta che ce ne sia uno e blocca tutti gli altri.
 * ____________________________________________________________________________________________________________
 *
 *
 */


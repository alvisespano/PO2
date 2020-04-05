package it.unive.dais.po2.myjdk;

import java.util.function.Function;


/**
 * Classe che fa veramente i set
 * non li fa l'interfaccia.
 * @param <T>
 */
public class MyHashSet<T> extends MyLinkedSet<T> {
    private HashFun<T> h;

    /**
     * Interfaccia che prende un Generic Type E
     * e restituisce un long (che non è un oggeto).
     * @param <E> generic type
     */
    public interface HashFun<E> {
        long hash(E e);
    }

    /**
     * NON-ANONYMUS ISTANCE
     * @param <E>
     */
    private static class DefaultHashFun<E> implements HashFun<E> {
        @Override
        public long hash(E e) {
            return e.hashCode();
        }
    }

    /**
     * NON-ANONYMUS NON-STATIC ISTANCE
     */
    private class DefaultHashFun__nonstatic implements HashFun<T> {
        @Override
        public long hash(T e) {
            return e.hashCode();
        }
    }

    /**
     * Costruttore
     * @param h è una funzione di Hashing per gli oggetti che metto nel mio hashSet.
     *          Fornire una funzione che produce un hash dato un elemento di quelli che
     *          vuoi mettere nell'hashSet, quindi da T a long
     */
    public MyHashSet(HashFun<T> h) {
        super();
        this.h = h;
    }


    /**
     * Costruttore
     * senza parametri che usa una classe di default hasCode() di Object.
     */
    public MyHashSet() {
        super();
        /**
         * TUTTE QUESTE ESPRESSIONI (qui sotto) SONO EQUIVALENTI
         * Sono 5 modi per rendere la classe HashSet in grado di
         * accettare una funzione di hashing come parametro di costruzione
         * in modo tale che l'utente possa definire la sua funzione di hash.
         *
         * RIFLESSIONE
         * Esiste già un modo previsto dal linguaggio per
         * customizzare la funzione di hashing di un determinato oggetto,
         * basta fare l'Override il metodo hashCode().
         *
         */

        /* LAMBDA SYNTAX
         * Se non si passa nessuna HashFun,
         * metto nel campo della hashFun una lambda astratta su un parametro x di tipo T,
         * il quale T è il mio Generic
         * che chiama il metodo hashCode() di Object su quella x
         */
        this.h = (T x) -> x.hashCode();

        /* METHOD REFERENCE SYNTAX
         * Method reference del metodo hashCode della classe di tipo T.
         * Crea una lambda che è il puntatore al metodo hashCode() degli oggetti di tipo T.
         */
        this.h = T::hashCode;   // l'operatore '::' serve ad avere il puntatore di
                                // a sinistra c'è il tipo
                                // Puntatore al metodo hashCode della classe T (Generic)
        /*
         * SPIEGAZIONE: esempio per operatore ::
         *
         * public static class Cane {
         *      // questo metodo è una funzione
         *      public void mangia() {
         *          // ...
         *
         *          public static void main() {
         *              Cane fido = new Cane();
         *              fido.mangia();  // l'operatore '.' chiama
         *              Cane::mangia;   // errore
         *          }
         *      }
         * }
         */

        // ANONTMUS CLASS SYNTAX
        this.h = new HashFun<T>() {
            @Override
            public long hash(T e) {
                return e.hashCode();
            }
        };


        // non-anonymous instance
        this.h = new DefaultHashFun<T>();
        // non-anonymous non-static instance
        this.h = new DefaultHashFun__nonstatic();
    }

    @Override
    /**
     * funzione che prende un elemento da aggiungere al Set,
     * controlla tutto il Set se quell'elemento c'è già,
     * se c'è non lo aggiunge, altrimenti lo aggiunge.
     * @param x generic type T
     */
    public void add(T x) {
        MyIterator<T> it = iterator();
        boolean found = false;
        while (it.hasNext()) {
            T e = it.next();
            //if (x.hashCode() == e.hashCode()) // il tipo hashCode è fornito da Object
            /*
            *  L'if qui sotto
            * */
            if (h.hash(x) == h.hash(e))
                found = true;
        }
        if (!found)
            l.add(x);
    }
}

package it.unive.dais.po2.other;

// parliamo delle normali classi del JDK ufficiale (non del nostro)
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RevArrayList<T> extends ArrayList<T> {
    /**
     * Costruttore (omittibile)
     * STUB
     */
    public RevArrayList() {
        super();
    }

    /**
     * Costruttore
     * @param cap capacità massima
     * STUB
     */
    public RevArrayList(int cap) {
        super(cap);
    }

    /**
     * Classe interna statica = Classe globale.
     * Non può vedere il this della calsse che lo contiene.
     * Questa classe funziona con qualsiasi List (non Collection perché non ha get).
     * @param <T>
     */
    public static class RevIterator__static<T> implements Iterator<T> {
        private List<T> l;
        private int pos;

        public RevIterator__static(List<T> l) {
            this.l = l;
            this.pos = l.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public T next() {
            return l.get(pos--);
        }
    }

    /**
     * Definizione di una classe interna non statica che
     * implementa Iterator<T> ovvero
     * il T della classe che la contiene.
     * Può vedere il this della classe che lo contiene
     */
    private class RevIterator__nonstatic implements Iterator<T> {

        private int pos = RevArrayList.this.size() - 1;
        /* RevArrayList.this.size()
        *  modo per riferirsi al this che non sei tu ma quello che mi contiene (this.metodo())
        *  usando il this di chi ci contiene posso usare size, non il contrario.
        *
        */

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public T next() {
            return RevArrayList.this.get(pos--);
            /*
            * quando una classe interna non è statica
            * vuol dire che
            *   - ha il this della classe che la contiene
            *   - può utilizzare tutti i generics della classe che la contiene.
            */
        }
    }

    /**
     * Metodo ereditato
     *      - come firma l'ho ereditato dall'interfaccia alta
     *          - Iterable / Collection / List / ArrayList
     *      -
     * @return anonymus class
     */
    @Override
    public Iterator<T> iterator() {
        //return new RevIterator<T>(this);            // classe globale
        //return new RevIterator__static<T>(this);    // classe nested STATICA
        //return new RevIterator__nonstatic();        // classe nested NON STATIC
        // this è la collection che voglio che iteri


        /* Dalla n del new al ; dopo la } è un espressione
        *  [return Espressione;]
        */
        return new Iterator<T>() {                    // anonymous class
            private int pos = size() - 1;
            /* pos:
            *  dichiarazione di un campo dell'oggetto anonimo che sto costruendo
            *  e non di una variabile.
            *  Questo campo pos contiene l'ultimo elemento quando si chiama il metodo per la prima volta.
            *  Perché si è costretti a fare un campo private private?
            *
            *  Come mai questa cosa funziona perché size() e get() di chi sono?
            *
            * */

            /**
             * Metodo iteratore che va al contrario.
             * @return true se pos >= 0 ; false se è arrivato all'inizio della lista
             */
            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public T next() {
                return get(pos--);
            }
        };
    }
}

/*
* ANONYMUS CLASS
*   Un anonymus class è un espressione (dal punto di vista sintattico)
*   avente la seguente sintassi:
*       - new
*       - nomeInterfaccia (non di una classe)
*       - <Eventuali Generics>
*       - ()
*       - {
 *               - @Override dei metodi dell'interfaccia
 *               - corpo fatto di metodi
*       - };
*   serve a creare al volo degli oggetti
*   a cui dare l'implementazione dei metodi,
*   senza definire una classe da qualche parte (Anonymus class).
*   Il TIPO che ha l'espressione è il tipo dell'interfaccia.
*
*/

/*
* essere STATICO: non avere il THIS
* essere NON STATICO: avere THIS
*/


/*
* PATTERN: COMMAND
*   L'aggancio tra iteratore e Command pattern sono le anonymus class.
*   Iterator: nome di un design pattern.
*   Anonymus Class: nome di un feature del linguaggio Java (non centra con i design pattern).
*/

/*
* Tra classe globale, classe non statica e anonymus class sono tre cose diverse.
*
*/


package it.unive.dais.po2.aa2020_21.tinyjdk;

public class ArrayList<T> extends AbstractResizableList<T> implements List<T> /*, Map<Integer, T>*/ {

    public ArrayList() {
        a = new Object[10];
        actualSize = 0;
    }

    @Override
    public void add(T x) {
        actualAdd(x);
    }

    private static class myStaticIterator<E> implements Iterator<E> {

        private final ArrayList<E> a;
        private int pos = 0;

        public myStaticIterator(ArrayList<E> a) {
            this.a = a;
        }

        @Override
        public boolean hasNext() {
            return pos < a.size();
        }

        @Override
        public E next() {
            return a.get(pos++);
        }
    }

    private class myIterator implements Iterator<T> {

        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < ArrayList.this.size();
        }

        @Override
        public T next() {
            return ArrayList.this.get(pos++);
        }
    }

    /*@Override
    public void put(Integer key, T value) {
        set(key, value);
    }

    @Override
    public T get(Integer key) throws PairMap.NotFoundException {
        return null;
    }

    @Override
    public void remove(Integer key) {

    }*/

    @Override
    public Iterator<T> iterator() {
        //return new ArrayListIterator<T>(this);        // versione con classe pubblica in un file a sé stante
        //return new myStaticIterator<T>(this);         // versione con classe nested STATICA: equivalente alla versione con classe pubblica su file
        //return new myIterator();                      // versione con classe nested NON-STATICA (non servono type arguments)

        // versione con classe ANONIMA
        final int zero = 0;                             // esempio di variabile locale usata dalla classe anonima
        return new Iterator<T>() {
            private int pos = zero;     // riferimento ad una variabile nello scope del metodo: la CHIUSURA dell'ambiente consente proprio questo, cioè l'oggetto costruito
                                        // tramite la classe anonima porta con sé un pezzo dello scope in cui è stato definito, a patto che l'accesso alle variabili
                                        // sia solamente in lettura e non le modifichi. Ecco perché devono essere final!

            @Override
            public boolean hasNext() {
                return pos < ArrayList.this.size();
            }

            @Override
            public T next() {
                return ArrayList.this.get(pos++);
            }
        };
    }







}

package it.unive.dais.po2.aa2020_21.tinyjdk;

public class ArrayList<T> implements List<T> {
    private Object[] a;
    private int actualSize;

    public ArrayList() {
        a = new Object[10];
        actualSize = 0;
    }


    @Override
    public void add(T x) {
        if (actualSize >= a.length) {
            Object[] newa = new Object[a.length * 2];
            for (int i = 0; i < a.length; ++i)
                newa[i] = a[i];
            a = newa;
        }
        a[actualSize++] = x;
    }

    @Override
    public boolean contains(T x) {
        for (Object o_ : a) {
            T o = (T) o_;
            if (o.equals(x)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public void remove(T x) {

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

    @Override
    public Iterator<T> iterator() {
        //return new ArrayListIterator<T>(this);        // versione con classe pubblica in un file a sé stante
        //return new myStaticIterator<T>(this);         // versione con classe nested STATICA: equivalente alla versione con classe pubblica su file
        //return new myIterator();                        // versione con classe nested NON-STATICA (non servono type arguments)

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

    @Override
    public T get(int index) {
        checkPos(index);
        return (T) a[index];
    }

    @Override
    public void set(int index, T x) {
        checkPos(index);
        a[index] = x;
    }

    private void checkPos(int index) {
        if (index < 0 || index >= size()) throw new RuntimeException(String.format("index %d is greater than size %d", index, size()));
    }
}

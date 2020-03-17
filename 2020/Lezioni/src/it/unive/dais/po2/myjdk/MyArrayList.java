package it.unive.dais.po2.myjdk;

/*
* Refactoring: significa prendere del codice (gerarchia di classi nel caso di Java) e
*   e riorganizzarle in modo da ripulirle e migliori.
*/

/**
 * MyArrayList è una lista di MyList senza puntatore al prossimo nodo
 * ma con un indice per identificare la cella
 * @param <T>
 */
public class MyArrayList<T> implements MyList<T> {

    private Object[] a;
    private int actualSize; // dimensione virtuale: vera dimensione dell'array

    public MyArrayList() {
        this.a = new Object[100];
        this.actualSize = 0;
    }

    @Override
    public T get(int i) throws OutOfBoundsException {
        if (i >= actualSize) throw new OutOfBoundsException();
        // noinspection unchecked <-- suppressed statement: IntelliJ spegne il warning
        return (T) a[i]; // cast non controllato
    }

    /**
     * Metodo size
     * @return actualsize: vera grandezza dell'array
     */
    @Override
    public int size() {
        return actualSize;
    }

    /**
     * Metodo add
     * @param x elemento da aggiungere all'array di Object
     */
    @Override
    public void add(T x) {
        /*
        * Se arrivo alla fine dell'array lo rialloco
        */
        if (actualSize >= a.length) {
            Object[] a2 = new Object[a.length + 100];
            /*
            *  Arraycopy è un metodo statico [IntelliJ scrive in italico i metodi statici]
            */
            System.arraycopy(a, 0, a2, 0, a.length);
            /* Manual copy of ARRAYCOPY
            *  for(int i = 0; i < a.length; ++i)
            *       a2[i] = a[i];
            *  a = a2;
            */
            a = a2; // BLIT: assegnamento fa perdere il riferimento al vecchio array che il garbage collector eliminerà
        }
        a[actualSize++] = x; // actualsize cresce di volta in volta
    }

    @Override
    public void add(int i, T x) {
        // TODO: da fare per casa
    }

    @Override
    public boolean remove(int i) {
        // TODO: da fare per casa
        return true;
    }

    @Override
    public boolean contains(T x) {
        // TODO: questa è una brutta replicazione: pensare ad un buon refactoring!
        MyIterator<T> it = iterator();
        while (it.hasNext()) {
            T e = it.next();
            if (x.equals(e)) return true;
        }
        return false;
    }

    @Override
    public boolean remove(T x) {
        return false;
    }

    @Override
    public void clear() {
        this.actualSize = 0;
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < actualSize;
            }

            @Override
            public T next() {
                //noinspection unchecked
                return (T) a[pos++];
            }
        };
    }
}

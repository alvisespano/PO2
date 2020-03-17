package it.unive.dais.po2.myjdk;

// TODO: togliere il prefisso My da tutti i nomi dei tipi e mostrare la differenza tra tipi omonimi in package diversi
public class MyArrayList<T> extends MyAbstractCollection<T> implements MyList<T> {

    private Object[] a;
    private int actualSize;

    public MyArrayList() {
        this.a = new Object[100];
        this.actualSize = 0;
    }



    @Override
    public T get(int i) throws OutOfBoundsException {
        if (i >= actualSize)
            throw new OutOfBoundsException("get: invalid position " + i);
        //noinspection unchecked
        return (T) a[i];
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public void add(T x) {
        if (actualSize >= a.length) {
            Object[] a2 = new Object[a.length + 100];
            System.arraycopy(a, 0, a2, 0, a.length);
            a = a2;
        }
        a[actualSize++] = x;
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

package tinyjdk.dais.unive.it;

public class ArrayList<T> implements List<T> {
    private T[] a;
    private int sz;

    public ArrayList() {
        clear();
    }

    @Override
    public void add(T o) {
        if (sz >= a.length) {
            T[] newa = (T[]) new Object[a.length * 2];
            for (int i = 0; i < a.length; i++)
                newa[i] = a[i];
            a = newa;
        }
        a[sz++] = o;
    }

    @Override
    public boolean contains(T o) {
        for (int i = 0; i < sz; i++) {
            T e = a[i];
            if (e.equals(o)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public void remove(T o) {
        // TODO
    }

    @Override
    public void clear() {
        a = (T[]) new Object[100];
        sz = 0;
    }

    @Override
    public T get(int i) {
        if (i >= 0 && i < sz)
            return a[i];
        throw new RuntimeException();
    }

    @Override
    public void set(int i, T e) {
        if (i >= 0 && i < sz)
            a[i] = e;
        throw new RuntimeException();
    }


    /*private class MyIterator implements Iterator<T> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < sz;
        }

        @Override
        public T next() {
            return a[pos++];
        }
    }*/

}

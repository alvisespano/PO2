package it.unive.dais.po2.tinyjdk;

public class ArrayList<T> implements List<T> {

    private Object[] a;
    private int sz;

    public ArrayList(int capacity) {
        a = new Object[capacity];
        sz = 0;
    }

    public ArrayList() {
        this(10);
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public void add(T x) {
        if (sz >= a.length) {
            Object[] old = a;
            a = new Object[old.length * 2];
            for (int i = 0; i < old.length; ++i)
                a[i] = old[i];
        }
        a[sz++] = x;
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public boolean contains(T x) {
        return false;
    }

    private class MyIterator implements Iterator<T> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            return get(pos++);
        }
    }

    private static class ListIterator<E> implements Iterator<E> {
        private int pos = 0;
        private List<E> l;

        public ListIterator(List<E> l) {
            this.l = l;
        }

        @Override
        public boolean hasNext() {
            return pos < l.size();
        }

        @Override
        public E next() {
            return l.get(pos++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        //return new MyIterator();
        //return new ListIterator<T>(this);
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size();
            }

            @Override
            public T next() {
                return get(pos++);
            }
        };
    }


    @Override
    public T get(int index) {
        return (T) a[index];
    }

    @Override
    public void set(int index, T x) {
        a[index] = x;
    }
}
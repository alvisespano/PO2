package tinyjdk;

public class ArrayList<T> implements List<T> {
    private Object[] a;
    private int sz;

    public ArrayList() {
        this.a = new Object[10];
        this.sz = 0;
    }

    @Override
    public void add(T x) {
        if (sz >= a.length) {
            Object[] old = a;
            a = new Object[a.length * 2];
            for (int i = 0; i < old.length; ++i)
                a[i] = old[i];
        }
        a[sz++] = x;
    }

    @Override
    public void clear() {
        sz = 0;
    }

    @Override
    public boolean contains(T x) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public int size() {
        return sz;
    }
    // static nested iterator
    private static class StaticMyIterator<T> implements Iterator<T> {
        private int pos = 0;
        private ArrayList<T> enclosing;

        public StaticMyIterator(ArrayList<T> a) {
            this.enclosing = a;
        }
        @Override
        public boolean hasNext() {
            return this.pos < enclosing.size();
        }
        @Override
        public T next() {
            return enclosing.get(pos++);
        }
    }

    // non-static nested iterator
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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    @Override
    public T get(int i) {
        if (i < sz)
            return (T) a[i];
        throw new RuntimeException(String.format("ArrayList.get(): index %d out of bounds %d", i, sz));
    }

    @Override
    public T set(int i, T x) {
        if (i < sz) {
            T old = get(i);
            a[i] = x;
            return old;
        }
        throw new RuntimeException(String.format("ArrayList.set(): index %d out of bounds %d", i, sz));
    }

    @Override
    public void add(int i, T x) {

    }

    @Override
    public T remove(int i) {
        return null;
    }
}

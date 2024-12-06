package tinyjdk;

public class ArrayList<T> implements List<T> {

    T[] a;
    int sz;

    public ArrayList() {
        this.a = (T[]) new Object[10];
        this.sz = 0;
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public void add(T e) {
        if (sz >= a.length) {
            T[] newa = (T[]) new Object[a.length * 2];
            for (int i = 0; i < a.length; ++i) {
                newa[i] = a[i];
            }
            a = newa;
        }
        a[sz++] = e;
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= sz) throw new IndexOutOfBoundsException();
        return a[i];
    }

    @Override
    public void set(int i, T e) {
        if (i < 0 || i >= sz) throw new IndexOutOfBoundsException();
        a[i] = e;
    }

    @Override
    public void clear() {
        sz = 0;
    }


    @Override
    public boolean contains(T e) {
        for (int i = 0; i < sz; ++i) {
            if (a[i].equals(e))
                return true;
        }
        return false;
    }


    @Override
    public void remove(T e) {
        for (int i = 0; i < sz; ++i) {
            if (a[i].equals(e)) {
                for (int j = i + 1; j < sz; ++j) {
                    a[j - 1] = a[j];
                }
                --sz;
                break;
            }
        }
    }


    private class MyIteratorNestedNonstatic implements Iterator<T> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < sz;
        }

        @Override
        public T next() {
            return a[pos++];
        }
    }

    private static class MyIteratorNestedStatic<E> implements Iterator<E> {
        private int pos = 0;
        private ArrayList<E> that;

        public MyIteratorNestedStatic(ArrayList<E> that) {
            this.that = that;
        }

        @Override
        public boolean hasNext() {
            return pos < that.sz;
        }

        @Override
        public E next() {
            return that.a[pos++];
        }
    }

    @Override
    public Iterator<T> iterator() {
        // con classe globale
        //return new ArrayListIterator<>(this);
        // con nested static
        //return new MyIteratorNestedStatic<>(this);
        // con nested non-static
        //return new MyIteratorNestedNonstatic();
        // con anonymous class
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < sz;
            }

            @Override
            public T next() {
                return a[pos++];
            }
        };
    }


    public static <T> void printAll(Iterable<T> c) {
        Iterator<T> it = c.iterator();
        while(it.hasNext()) {
            T n = it.next();
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        Iterable<Integer> l = new ArrayList<Integer>();

        Iterator<Integer> it = l.iterator();
        while(it.hasNext()) {
            Integer n = it.next();
            System.out.println(n);
        }
    }

}

package tinyjdk;

public class ArrayList<T> implements List<T> {

    private T[] a;
    private int sz;

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


    @Override
    public Iterator<T> iterator() {
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
}

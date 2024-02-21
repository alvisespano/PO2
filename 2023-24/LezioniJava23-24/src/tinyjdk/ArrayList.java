package tinyjdk;

public class ArrayList<T> implements List<T> {
    private Object[] a;
    private int sz;

    public ArrayList() {
        this.a = new Object[10];
        sz = 0;
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
        return false;
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T get(int i) {
        return (T) a[i];
    }

    @Override
    public T set(int i, T x) {
        return null;
    }

    @Override
    public void add(int i, T x) {

    }

    @Override
    public T remove(int i) {
        return null;
    }
}

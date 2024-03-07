package tinyjdk;

public abstract class AbstractResizableArray<T> implements Collection<T> {
    protected Object[] a;
    protected int sz;

    public AbstractResizableArray() {
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
    public boolean isEmpty() {
        return sz == 0;
    }

    public abstract void remove(T x);

    @Override
    public int size() {
        return sz;
    }

    public abstract Iterator<T> iterator();
}

package tinyjdk;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private Object[] a;
    private int pos;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        assert capacity > 0;
        a = new Object[capacity];
        pos = 0;
    }

    @Override
    public void add(T e) {
        if (pos >= a.length)
            a = Arrays.copyOf(a, a.length * 2);
        a[pos++] = e;
    }

    @Override
    public int indexOf(T e) throws NotFoundException {
        for (int i = 0; i < pos; ++i)
            if (a[i].equals(e))
                return i;
        throw new NotFoundException();
    }

    @Override
    public void remove(T e) throws NotFoundException {
        int i = indexOf(e);
        // TODO: ricompattare l'array
    }

    @Override
    public boolean contains(T e) {
        return false;
    }

    @Override
    public void clear() {
        a = new Object[pos + 1];
    }

    @Override
    public int size() {
        return pos;
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
    public void set(int i, T e) {
        a[i] = e;
    }
}

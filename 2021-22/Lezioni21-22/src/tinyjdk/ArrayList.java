package tinyjdk;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private Object[] a;
    private int actualLen;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        assert capacity > 0;
        a = new Object[capacity];
        actualLen = 0;
    }

    @Override
    public void add(T e) {
        if (actualLen >= a.length)
            a = Arrays.copyOf(a, a.length * 2);
        a[actualLen++] = e;
    }

    @Override
    public int indexOf(T e) throws NotFoundException {
        for (int i = 0; i < actualLen; ++i)
            if (a[i].equals(e))
                return i;
        throw new NotFoundException();
    }

    @Override
    public void remove(T e) throws NotFoundException {
        int i = indexOf(e);
        for (int j = i; j < size() - 1; ++j)
            a[j] = a[j + 1];
        --actualLen;
    }

    @Override
    public boolean contains(T e) {
        try {
            indexOf(e);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

    @Override
    public void clear() {
        a = new Object[actualLen + 1];
    }

    @Override
    public int size() {
        return actualLen;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < actualLen;
            }

            @Override
            public T next() {
                return (T) a[pos++];
            }
        };
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

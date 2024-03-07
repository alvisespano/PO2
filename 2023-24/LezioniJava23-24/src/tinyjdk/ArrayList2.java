package tinyjdk;

public class ArrayList2<T> extends AbstractResizableArray<T> implements List<T> {
    @Override
    public void remove(T x) {
        for (int i = 0; i < size(); ++i) {
            T o = get(i);
            if (o.equals(x)) {
                for (int j = i ; j < size() - 1; ++j)
                    set(j, get(j + 1));
                --sz;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
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
        // TODO
    }

    @Override
    public T remove(int i) {
        // TODO
        return null;
    }
}

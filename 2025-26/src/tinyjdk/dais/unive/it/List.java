package tinyjdk.dais.unive.it;

public interface List<T> extends Collection<T> {
    T get(int index);
    void set(int index, T element);

    default Iterator<T> iterator() {
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
}

package tinyjdk.dais.unive.it;

public interface Collection<T> extends Iterable<T> {
    void add(T o);
    boolean contains(T o);
    int size();
    void remove(T o);
    void clear();

    default boolean isEmpty() {
        return size() == 0;
    }
}

package tinyjdk;

public interface Collection<T> extends Iterable<T> {
    void add(T x);

    default void addAll(Collection<T> c) {
        Iterator<T> it = c.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }
    void clear();
    boolean contains(T x);
    boolean isEmpty();
    void remove(T x);
    int size();
}

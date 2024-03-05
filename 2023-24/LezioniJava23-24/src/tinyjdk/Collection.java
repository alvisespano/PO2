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
    default boolean contains(T x) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T e = it.next();
            if (e.equals(x))
                return true;
        }
        return false;
    }
    boolean isEmpty();
    void remove(T x);
    int size();
}

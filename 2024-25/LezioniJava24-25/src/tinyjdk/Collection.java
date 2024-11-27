package tinyjdk;

public interface Collection<T> extends Iterable<T> {
    void add(T e);
    boolean contains(T e);
    int size();
    void remove(T e);
    void clear();
}

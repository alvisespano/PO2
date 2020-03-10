package it.unive.dais.po2.myjdk;

public interface MyCollection<T> extends MyIterable<T> {

    void add(T x);
    int size();
    boolean contains(T x);
    boolean remove(T x);
    void clear();

    default boolean isEmpty() {
        return size() == 0;
    }
}

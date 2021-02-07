package it.unive.dais.po2.myjdk;

public interface MyList<T> extends MyCollection<T> {
    T get(int i) throws OutOfBoundsException;
    void add(int i, T x);
    boolean remove(int i);
}

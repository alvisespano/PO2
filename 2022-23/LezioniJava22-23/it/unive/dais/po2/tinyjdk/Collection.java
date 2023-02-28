package it.unive.dais.po2.tinyjdk;

public interface Collection<T> extends Iterable<T> {
    int size();

    void add(T x);

    void remove(T x);

    boolean contains(T x);
}

package it.unive.dais.po2.tinyjdk;

import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
    int size();

    default boolean isEmpty() { return size() == 0; }

    void add(T x);

    void remove(T x);

    boolean contains(T x);
    boolean contains(Predicate<T> p);
}

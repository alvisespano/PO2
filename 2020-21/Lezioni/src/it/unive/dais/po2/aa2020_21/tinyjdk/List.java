package it.unive.dais.po2.aa2020_21.tinyjdk;

public interface List<T> extends Collection<T> {
    T get(int index);
    void set(int index, T x);
}

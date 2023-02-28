package it.unive.dais.po2.tinyjdk;

public interface List<T> extends Collection<T> {
    T get(int index);

    void set(int index, T x);

    void removeAt(int index);
}

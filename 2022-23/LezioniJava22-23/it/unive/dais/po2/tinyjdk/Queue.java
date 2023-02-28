package it.unive.dais.po2.tinyjdk;

public interface Queue<T> extends Collection<T> {
    void push(T x);
    T pop();
    T peek();
}

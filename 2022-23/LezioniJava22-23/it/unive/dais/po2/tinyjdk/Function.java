package it.unive.dais.po2.tinyjdk;

@FunctionalInterface
public interface Function<A, B> {
    B apply(A x);
}

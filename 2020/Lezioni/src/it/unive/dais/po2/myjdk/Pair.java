package it.unive.dais.po2.myjdk;

/**
 * Classe per [il tipo delle] coppie
 * @param <A> first: primo campo della coppia;
 * @param <B> second: secondo campo della coppia.
 */
// Classe per coppie IMMUTABILI
public class Pair<A, B> {
    public A first;
    public B second;

    public Pair(A a, B b) {
        this.first = a;
        this.second = b;
    }
}

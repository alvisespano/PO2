package it.unive.dais.po2.myjdk;

// Classe per coppie IMMUTABILI
public class Pair<A, B> {
    public final A first;
    public final B second;

    public Pair(A a, B b) {
        this.first = a;
        this.second = b;
    }

}

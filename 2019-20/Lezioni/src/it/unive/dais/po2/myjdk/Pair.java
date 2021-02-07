package it.unive.dais.po2.myjdk;

// Classe per coppie IMMUTABILI
public class Pair<A, B> {
    public A first;
    public B second;

    public Pair(A a, B b) {
        this.first = a;
        this.second = b;
    }

}

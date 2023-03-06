package it.unive.dais.po2.tinyjdk;

public class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(A a, B b) {
        this.fst = a;
        this.snd = b;
    }
}

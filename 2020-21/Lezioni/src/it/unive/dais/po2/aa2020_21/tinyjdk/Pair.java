package it.unive.dais.po2.aa2020_21.tinyjdk;

public class Pair<A, B> {
    public final A first;
    public final B second;
    public Pair(A a, B b) {
        first = a;
        second = b;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            @SuppressWarnings("rawtypes")
            Pair p = (Pair) o;
            return first.equals(p.first) && second.equals(p.second);
        }
        return false;
    }
}

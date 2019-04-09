package it.unive.dais.po.pitagora;

public class Lato<T extends Numeric> {
    private T x = null;

    public void set(T x) {
        if (x.isLessThan(0.0)) throw new IllegalArgumentException();
        this.x = x;
    }

    public T get() {
        return x;
    }

    public boolean isInitialized() {
        return x.isGreaterThan(-1.0);
    }

    @Override
    public String toString() {
        return String.format("Lato(%s)", x);
    }

    public T square() {
        return x.multiply(x);
    }
}

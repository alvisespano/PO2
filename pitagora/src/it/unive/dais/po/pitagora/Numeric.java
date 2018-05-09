package it.unive.dais.po.pitagora;

public interface Numeric {

    boolean isLessThan(double v);
    boolean isGreaterThan(double v);

    <T extends Numeric> T multiply(T x);
}

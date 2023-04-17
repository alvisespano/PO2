package it.unive.dais.po2.lambdas;

import it.unive.dais.po2.Zoo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Wildcards {

    public static <T> T identity(T x) {
        return x;
    }

    public static Object identity2(Object x) {
        return x;
    }

    public static Collection<?> w1(Collection<? extends Number> x,
                                   Collection<? extends Number> y) {

    }

    public static <X extends Number> Collection<?> w2(Collection<X> x,
                                                      Collection<X> y) {

    }

    public static Collection<?> w3(Collection<? super Number> x,
                                   Collection<? super Number> y) {

    }

    public static void main1(String[] args) {
        List<? extends Zoo.Animal> l = new ArrayList<>();
        Zoo.Dog fido = new Zoo.Dog(30, "red");
        l.add(fido);
        Zoo.Animal pluto = l.get(0);

        List<? super Zoo.Dog> l2 = new ArrayList<>();
        l2.add(fido);
        Object rex = l2.get(0);
    }

    public static Integer f(Integer x) {
        return x + 1;
    }

    public static Integer f2(Number x) {
    }

    public static <A, B> Collection<B> map(Collection<A> c,
                                           Function<? super A, ? extends B> f) {
        Collection<B> r = new ArrayList<>();
        for (A x : c) {
            r.add(f.apply(x));
        }
        return r;
    }


    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3);
        Collection<Number> u = map(l, (Number x) -> 1);

    }

    public static class Animale {
        public Animale accoppia(Animale a) {}
    }

    public static class Gatto extends Animale {

        @Override
        public Gatto accoppia(Object a) { /* implementazione diversa */ }
    }

}

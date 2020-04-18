package it.unive.dais.po2.other;

import it.unive.dais.po2.zoo.Animal;
import it.unive.dais.po2.zoo.ColoredAnimal;
import it.unive.dais.po2.zoo.Dog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalPrimitives {

    //public static <A, B, C extends B> List<B> map(Iterable<A> l, Function<? super A, C          > f) {
    public static <A, B> List<B> map(Iterable<A> l, Function<? super A, ? extends B> f) {
        List<B> c = new ArrayList<>();
        for (A a : l) {
            B b = f.apply(a);
            c.add(b);
        }
        return c;
    }

    public static <A, B> B fold(Iterable<A> l, B zero, BiFunction<A, B, B> f) {
        B acc = zero;
        for (A a : l) {
            acc = f.apply(a, acc);
        }
        return acc;
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l1.add(i);
        }
        Collection<Integer> r1 = map(l1, (x) -> x * 2);
        int sum = fold(r1, 0, (x, acc) -> x + acc);


        List<Dog> l2 = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l2.add(new Dog(i, "red"));
        }
        List<Animal> r2 = map(l2, (ColoredAnimal x) -> new Dog(x.getWeight() - 5, x.getColor()));


    }

}

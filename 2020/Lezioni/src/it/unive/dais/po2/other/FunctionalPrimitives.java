package it.unive.dais.po2.other;

import it.unive.dais.po2.zoo.Animal;
import it.unive.dais.po2.zoo.ColoredAnimal;
import it.unive.dais.po2.zoo.Dog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalPrimitives {

    public static <A, B> List<B> map(Iterable<A> l, Function<? super A, ? extends B> f) {
        List<B> c = new ArrayList<>();
        for (A a : l) {
            B b = f.apply(a);
            c.add(b);
        }
        return c;
    }

    public static <A, B> List<B> map2(Iterable<A> l, Function<? super A, ? extends B> f) {
        return fold(l, new ArrayList<>(), (x, acc) -> {
            acc.add(f.apply(x));
            return acc;
        });
    }

    public static <A, B> B fold(Iterable<A> l, B zero, BiFunction<A, B, B> f) {
        B acc = zero;
        for (A a : l) {
            acc = f.apply(a, acc);
        }
        return acc;
    }

    public static <A, B> B fold_recur(Iterable<A> l, B zero, BiFunction<A, B, B> f) {
        return fold_recur(l.iterator(), zero, f);
    }

    public static <A, B> B fold_recur(Iterator<A> it, B zero, BiFunction<A, B, B> f) {
        return it.hasNext() ? fold_recur(it, f.apply(it.next(), zero), f) : zero;
    }

    public static void f() {                    // function_f:
        int i = 0;                              // li r0, #0
        while (i < 10) {                        // loop:
                                                // cmp r0, #10
                                                // jeq exit
            System.out.println("ciao " + i);    // call ...
            i = i + 1;                          // add r0, #1, r0
        }                                       // jmp loop
                                                // exit:
                                                //      balbalbla
    }

    public static void g(int i) {               // function_g:
        if (i > 0) {                            // ld sp, r0
                                                // cmp r0, #0
                                                // jeq exit
            System.out.println("ciao " + i);    // call ...
            g(i - 1);                         // sub r0, #1
                                                // st r0, sp
        }                                       // jmp function_g
                                                // exit:
                                                //    blablabla
    }

    public static void main(String[] args) {

        g(10);                                  // st #10, sp
                                                  // jmp function_g

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

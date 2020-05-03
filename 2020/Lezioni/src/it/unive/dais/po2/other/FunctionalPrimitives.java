package it.unive.dais.po2.other;

import it.unive.dais.po2.zoo.Animal;
import it.unive.dais.po2.zoo.ColoredAnimal;
import it.unive.dais.po2.zoo.Dog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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

    public static <A> void filter__imperative(Iterable<A> l, Function<A, Boolean> p) {
        Iterator<A> it = l.iterator();
        while (it.hasNext()) {
            A a = it.next();
            if (!p.apply(a)) it.remove();
        }
    }

    public static <A> List<A> filter__pure(Iterable<A> l, Function<A, Boolean> p) {
        List<A> r = new ArrayList<>();
        for (A a : l) {
            if (p.apply(a)) r.add(a);
        }
        return r;
    }

    public static <A> void iter(Iterable<A> l, Consumer<A> f) {
        for (A a : l) {
            f.accept(a);
        }
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

    // =======================================================================
    // Spiegazione funzionamento dell'assegnamento

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

    // =======================================================================
    @FunctionalInterface
    interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
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
        /* Mappa una lista di Dog
         * in un'altra lista di Dog
         */
        List<Animal> r2 = map(l2, (ColoredAnimal x) -> new Dog(x.getWeight() - 5, x.getColor()));

        iter(l2, (Dog d) -> System.out.println(d));
        for (Dog d : l2) System.out.println(d);
        List<?> r = map(l2, (Dog d) -> { System.out.println(d); return null; });


        String s = "ciao";
        boolean b = s.isEmpty();
        Function<String, Boolean> f = String::isEmpty;
        Predicate<String> p = String::isEmpty;

        int i = s.indexOf('c');
        BiFunction<String, Character, Integer> g = String::indexOf;

        int j = s.lastIndexOf('c', 8);
        TriFunction<String, Character, Integer, Integer> h = String::lastIndexOf;

        {
            List<String> l = new ArrayList<>();
            l.add("pippo");
            l.add("baudo");
            l.add("pluto");
            String s2 = String.join(",", l);
            BiFunction<CharSequence, Iterable<? extends CharSequence>, String> k = String::join;
        }

        Function<Integer, Integer> id = Function.identity();
        Function<?, ?> id2 = (x) -> x;  // fun x -> x

    }

}

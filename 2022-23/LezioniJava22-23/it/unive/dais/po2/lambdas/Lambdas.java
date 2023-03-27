package it.unive.dais.po2.lambdas;

import it.unive.dais.po2.tinyjdk.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class Lambdas {

    /*interface Function<A, B> {
        B apply(A x);
    }*/

    /*interface Consumer<T> {
        void accept(T x);
    }*/

    /*interface Supplier<T> {
        T get();
    }*/

    /*interface Runnable {
        void run();
    }*/

    public static void main(String[] args) {

        Runnable r = () -> { System.out.println("ciao"); };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("ciao");
            }
        };

        Supplier<Integer> h = () -> 1;
        Supplier<Integer> h2 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1;
            }
        };

        Consumer<Integer> g = x -> { System.out.println(x); };
        Consumer<Integer> g2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> f2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        };

    }

    public static <A, B> Collection<B> map(Collection<A> c,
                                           Function<A, B> f) {
        Collection<B> r = new ArrayList<>();
        for (A x : c) {
            r.add(f.apply(x));
        }
        return r;
    }

    public static <A, B> Iterator<B> mapIterator(Iterator<A> it,
                                                 Function<A, B> f) {
        return new Iterator<B>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public B next() {
                return f.apply(it.next());
            }
        };
    }

    public static <T> Collection<T> filter__pure(Collection<T> c,
                                                 Predicate<T> p) {
        Collection<T> r = new ArrayList<>();
        for (T x : c) {
            if (p.test(x))
                r.add(x);
        }
        return r;
    }

    public static <T> void filter__impure(Collection<T> c,
                                          Predicate<T> p) {
        Iterator<T> it = c.iterator();
        while (it.hasNext()) {
            if (!p.test(it.next()))
                it.remove();
        }
    }

    public static <T> void iter(Collection<T> c, Consumer<T> f) {
        for (T x : c) {
            f.accept(x);
        }
    }

    public static <T> T sum(Collection<T> c, T zero, BiFunction<T, T, T> f) {
        return foldLeft(c, zero, f);
    }

    public static <A, B> B foldLeft(Collection<A> c,
                                    B zero,
                                    BiFunction<A, B, B> f) {
        B z = zero;
        for (A x : c) {
            z = f.apply(x, z);
        }
        return z;
    }

    // TODO scrivere la foldLeft ricorsivamente

    public static void main2(String[] args) {
        List<String> l = new ArrayList<>();
        l.add("ciao");
        l.add("mi");
        l.add("chiamo");
        l.add("pippo");
        String s = sum(l, "", (x, y) -> x + y);

    }
}

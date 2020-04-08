package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalTest {

    public static <A, B> Collection<B> map(Collection<A> l, Function<A, B> f) {
        Collection<B> c = new ArrayList<>();
        for (A a : l) {
            B b = f.apply(a);
            c.add(b);
        }
        return c;
    }

    public static <X> void print(Collection<X> c) {
        map(c, new Function<X, Void>() {
            @Override
            public Void apply(X x) {
                System.out.println(x);
                return null;
            }
        });

    }

    private static class MiaFunzionePerMap implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer x) {
            return x + 1;
        }
    }


    public static void main(String[] args) {

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }
        print(l);

        // LE 4 FORME DI LAMBDA IN JAVA
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> f2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        };

        Supplier<Integer> g = () -> {
            if (l.size() > 4) return 1;
            else return 2;
        };
        Supplier<Integer> g2 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                if (l.size() > 4) return 1;
                else return 2;
            }
        };

        Consumer<Integer> h = (x) -> {
            for (int i = 0; i < x; ++i)
                System.out.println(i);
        };
        Consumer<Integer> h2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                for (int i = 0; i < x; ++i)
                    System.out.println(i);
            }
        };

        Runnable r = () -> {
            System.out.println("ciao");
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("ciao");
            }
        };


        // ESEMPI DI CHIAMATA ALLA map()

        // questa è la stessa cosa fatta con una lambda
        Collection<Integer> x1 = map(l, x -> x + 1);
        // questa è con la classe anonima
        Collection<Integer> x2 = map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });
        // questa terza variante usa direttamente una classe NON-anonima
        Collection<Integer> x3 = map(l, new MiaFunzionePerMap());

        print(l);

        /*
        TRADUZIONE DA ANONYMOUS CLASS A LAMBDA
        LA LAMBDA E' UNO ZUCCHERO SINTATTICO PER UNA ANONYMOUS CLASS
        Collection<B> r = map(l, new Function<A, B>() {
            @Override
            public B apply(A x) {
                BODY;
            }
        });

        Collection<B> r = map(l, x -> BODY);
        */

    }
}

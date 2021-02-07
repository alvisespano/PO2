package esercizi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ScrittoGennaio2019 {

    /*1a1*/
    @FunctionalInterface
    public interface Function<A, B> {
        B apply(A x);
    }

    /*1a2*/
    public static <A, B> Iterator<B> mapIterator(Iterator<A> it, Function<A, B> f) {
        return new Iterator<>() {
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

    /*1b1*/
    public static class Pair<A, B> {
        public final A a;
        public final B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    /*1b2*/
    public static <A> Iterator<Pair<A, A>> pairIterator(Iterator<A> it) {
        return new Iterator<>()/*se non specifico non riesce ad inferire*/ {
            A last;

            @Override
            public boolean hasNext() {
                if (it.hasNext())
                    last = it.next();
                return it.hasNext();
            }

            @Override
            public Pair<A, A> next() {
                return new Pair<>(last, it.next());
            }
        };
    }

    /*1c*/
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; ++i)
            al.add(rnd.nextInt(10));

        Iterator<Pair<Integer, Integer>> cateti = pairIterator(al.iterator());

        Iterator<Double> ipotenuse = mapIterator(cateti, p -> Math.sqrt(p.a * p.a + p.b * p.b));

        while (ipotenuse.hasNext())
            System.out.println("Ipotenusa " + ipotenuse.next());
    }
}


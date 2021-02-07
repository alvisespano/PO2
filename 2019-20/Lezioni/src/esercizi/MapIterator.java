package esercizi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class MapIterator {



    public static <A, B> Iterator<B> mapIterator(Iterator<A> it, Function<A, B> f) {
        return new Iterator<B>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public B next() {
                try {
                    A a = it.next();
                    return f.apply(a);
                }
                catch (RuntimeException e) {
                    System.err.println(String.format("exception caught: %s", e.getMessage()));
                    if (hasNext()) return next();
                    else throw e;
                }
            }
        };
    }


    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        for (int i = -10; i < 20; ++i) {
            l.add(i);
        }
        Iterator<Double> it = mapIterator(l.iterator(), (n) -> (double) n);
        while (it.hasNext()) {
            double x = it.next();
            System.out.println(x);
        }

        Iterator<Double> it2 = mapIterator(l.iterator(),
                new Function<>() {
                    @Override
                    public Double apply(Integer n) {
                        if (n > 0) return (double) n;
                        else throw new RuntimeException();
                    }
                });
        while (it2.hasNext()) {
            double x = it2.next();
            System.out.println(x);
        }


    }

}

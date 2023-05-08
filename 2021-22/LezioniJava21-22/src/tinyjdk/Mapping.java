package tinyjdk;

import java.util.function.Function;
import java.util.List;
import java.util.Iterator;
import java.util.function.Supplier;

public class Mapping {


    public static <A, B> Iterator<B> mapIterator(Iterator<A> it, Function<A, B> f) {
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

    public static <A, B> Iterator<Supplier<B>> mapIterator__mt(Iterator<A> it, Function<A, B> f) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Supplier<B> next() {
                A a = it.next();
                final B[] b = (B[]) new Object[1];
                Thread t = new Thread(() -> {
                    b[0] = f.apply(a);
                });
                t.start();
                return () -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return b[0];
                };
            }
        };
    }


    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3, 4, 5);
        Iterator<String> it1 = mapIterator(l.iterator(), (n) -> String.format("%d", n));

        Iterator<Supplier<String>> it2 = mapIterator__mt(l.iterator(), (n) -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.format("%d", n);
        });

        while(it2.hasNext()) {
            Supplier<String> sup = it2.next();
            System.out.println(sup.get());
        }
    }


}

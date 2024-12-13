package misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Functional {

    public interface Function<A, B> {
        B apply(A x);
    }

    public interface Consumer<T> {
        void apply(T x);
    }

    public static <A, B> Collection<B> map(Collection<A> c, Function<A, B> f) {
        Collection<B> r = new ArrayList<B>();
        for (A a : c) {
            B b = f.apply(a);
            r.add(b);
        }
        return r;
    }

    public static <T> void iter(Collection<T> c, Consumer<T> f) {
        for (T x : c) {
            f.apply(x);
        }
    }

    public static <T> Collection<T> filter(Collection<T> c, Function<T, Boolean> f) {
        Collection<T> r = new ArrayList<>();
        for (T x : c) {
            if (f.apply(x)) {
                r.add(x);
            }
        }
        return r;
    }


    public static void main(String[] args) {
        {
            List<String> l = List.of("ciao", "sono", "franco", "!");
            Collection<Integer> r = map(l, new Function<>() {
                public Integer apply(String x) {
                    return x.length();
                }
            });
        }

        {
            List<Integer> l = List.of(-56, 345, 11, 0, -456, 23);
            Collection<Boolean> r = map(l, new Function<>() {
                public Boolean apply(Integer x) {
                    return x > 0;
                }
            });
        }

        {
            List<Integer> l = List.of(-56, 345, 11, 0, -456, 23);
            iter(l, new Consumer<Integer>() {
                public void apply(Integer x) {
                    System.out.println(x);
                }
            });
        }

        {
            List<Integer> l = List.of(-56, 345, 11, 0, -456, 23);
            Collection<Integer> r = filter(l, new Function<Integer, Boolean>() {
                public Boolean apply(Integer x) {
                    return x < 0;
                }
            });
        }

    }



}

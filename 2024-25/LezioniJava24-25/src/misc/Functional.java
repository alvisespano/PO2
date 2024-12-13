package misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

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

    public interface BiFunction<A, B, C> {
        C apply(A a, B b);
    }

    public static <T, R> R fold(Collection<T> c, R zero, BiFunction<T, R, R> f) {
        R r = zero;
        for (T x : c) {
            r = f.apply(x, r);
        }
        return r;
    }


    public static void main(String[] args) {
        {
            List<Integer> l = List.of(1, 2, 3, 4, 5);
            String r = fold(l, "", new BiFunction<>() {
                public String apply(Integer x, String acc) {
                    return acc.concat(x.toString());
                }
            });
        }

        {
            List<Integer> l = List.of(-56, 345, 11, 0, -456, 23);
            int r = fold(l, 1, new BiFunction<Integer, Integer, Integer>() {
                public Integer apply(Integer x, Integer acc) {
                    return x * acc;
                }
            });
        }

        {
            List<Double> l = List.of(-56.567, 345.356, 11.3254, 0.0, -456.345, 23.345);
            double r = fold(l, 0.0, new BiFunction<>() {
                public Double apply(Double x, Double acc) {
                    return x + acc;
                }
            });
        }

        {
            List<String> l = List.of("ciao", "sono", "franco", "!");
            String r = fold(l, "", new BiFunction<>() {
                public String apply(String x, String acc) {
                    return x.concat(acc);
                }
            });
        }


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
            Collection<Boolean> r = map(l, new Function<Integer, Boolean>() {
                public Boolean apply(Integer x) {
                    return x > 0;
                }
            });
            Collection<Boolean> r = map(l, (x) -> x > 0);
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

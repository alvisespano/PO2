import java.util.Iterator;
import java.util.function.Function;

public class Main {

    // 1.a
    public static class Pair<A, B> {
        public final A fst;
        public final B snd;

        public Pair(A a, B b) {
            fst = a;
            snd = b;
        }
    }

    public static class FunSeq<X extends Number & Comparable<? super X>, Y extends Number>
        implements Iterable<Pair<X, Y>> {

        private final X a, b;
        private final Function<? super X, ? extends Y> f;
        private final Function<X, X> inc;

        public FunSeq(X a, X b, Function<? super X, ? extends Y> f, Function<X, X> inc) {
            this.a = a;
            this.b = b;
            this.f = f;
            this.inc = inc;
        }

        // 1.b
        @Override
        public Iterator<Pair<X, Y>> iterator() {
            return new Iterator<>() {
                private X x = a;
                @Override
                public boolean hasNext() {
                    return x.compareTo(b) < 0;
                }

                @Override
                public Pair<X, Y> next() {
                    Pair<X, Y> r = new Pair<>(x, f.apply(x));
                    x = inc.apply(x);
                    return r;
                }
            };
        }
    }

    // 1.c
    public static void test1() {
        for (Pair<Double, Double> p : new FunSeq<>(-2., 2., (x) -> x * x - 2 * x + 1, (x) -> x + 0.1)) {
            final double x = p.fst, y = p.snd;
            System.out.printf("f(%g) = %g\n", x, y);
        }
    }

    // 1.d
    public static void test2() {
        for (Pair<Double, Integer> p : new FunSeq<>(-2., 2., (x) -> (int) (x * x - 2 * x + 1), (x) -> x + 0.1)) {
            final double x = p.fst;
            final int y = p.snd;
            System.out.printf("f(%g) = %d\n", x, y);
        }
    }


    public static void main(String[] args) {
        test1();
        test2();
    }
}












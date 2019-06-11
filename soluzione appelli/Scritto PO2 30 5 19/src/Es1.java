import java.util.*;

public class Es1 {


    // 1.a
    @FunctionalInterface
    public interface BiFunction<A, B, C> {
        C apply(A a, B b);
    }

    // 1.b
    public static class Pair<A, B> {
        public final A fst;
        public final B snd;

        public Pair(A a, B b) {
            this.fst = a;
            this.snd = b;
        }
    }

    // 1.c
    public static class Triple<A, B, C> extends Pair<A, B> {
        public final C trd;

        public Triple(A a, B b, C c) {
            super(a, b);
            this.trd = c;
        }
    }

    // 1.d
    public static <A, B, C> Iterator<Triple<A, B, C>> evalIterator(Iterator<Pair<A, B>> it, BiFunction<A, B, C> f) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Triple<A, B, C> next() {
                Pair<A, B> p = it.next();
                return new Triple<>(p.fst, p.snd, f.apply(p.fst, p.snd));
            }
        };
    }

}

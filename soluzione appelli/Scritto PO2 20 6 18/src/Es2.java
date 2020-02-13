
import java.util.Comparator;
import java.util.List;

public class Es2 {

    // 2.a
    public static class Pair<A, B> { // versione con campi pubblici immutabili, ma si potevano fare anche i getter volendo
        public final A a;
        public final B b;
        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    // 2.b
    public static <E> Pair<E, E> findMinAndMax(List<? extends E> l, Comparator<E> c) {
        E min = l.get(0), max = min;
        for (E x : l) {
            if (c.compare(x, max) > 0) max = x;
            if (c.compare(x, min) < 0) min = x;
        }
        return new Pair<>(min, max);
    }

    // 2.c
    public static <E extends Comparable<E>> Pair<E, E> findMinAndMax(List<? extends E> l) {
        return findMinAndMax(l, new Comparator<>() {
            @Override
            public int compare(E a, E b) {
                return a.compareTo(b);
            }
        });
    }

}

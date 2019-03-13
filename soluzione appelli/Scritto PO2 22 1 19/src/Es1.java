import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Es1 {

    // a.i
    public interface Function<A, B> {
        B apply(A x);
    }

    // b.i
    public static class Pair<A, B> {
        public final A fst;
        public final B snd;

        public Pair(A a, B b) {
            this.fst = a;
            this.snd = b;
        }
    }

    // a.ii
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

    // b.ii
    public static <A> Iterator<Pair<A, A>> pairIterator(Iterator<A> it) {
        return new Iterator<>() {
            A last;

            @Override
            public boolean hasNext() {
                if (it.hasNext()) last = it.next();
                return it.hasNext();
            }

            @Override
            public Pair<A, A> next() {
                return new Pair<>(last, it.next());
            }
        };
    }

    // c
    public static void main(String[] args) {
        Random rnd = new Random();
        List<Integer> c = new ArrayList<>();
        final int size = Math.abs(rnd.nextInt() % 50) + 1;
        for (int i = 0; i < size; ++i)
            c.add(Math.abs(rnd.nextInt() % 1000));
        System.out.println(String.format("lista di cateti (size = %d):\n%s", c.size(), c));

        Iterator<Pair<Integer, Integer>> cateti = pairIterator(c.iterator());
        Iterator<Double> ipotenuse = mapIterator(cateti, (Pair<Integer, Integer> p) -> Math.sqrt(Math.pow(p.fst, 2) + Math.pow(p.snd, 2)));

        for (int cnt = 1; ipotenuse.hasNext(); ++cnt) {
            double i = ipotenuse.next();
            System.out.println(String.format("ipotenusa #%d: %g", cnt, i));
        }
    }

}

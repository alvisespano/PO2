import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Es1 {

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

    public static <T> void forEach(Iterable<T> it, Consumer<T> f) {
        for (T x : it)
            f.accept(x);
    }

    public static class Pair<X, Y> {
        public final X fst;
        public final Y snd;
        public Pair(X x, Y y) {
            fst = x;
            snd = y;
        }
    }

    public static void main1(String[] args) {
        List<Pair<Function<String, Integer>, String>> l = new ArrayList<>();
        Iterator<Integer> it = mapIterator(l.iterator(), (p) -> p.fst.apply(p.snd));
    }

    public static <A, B> Iterator<B> applyFuns(Iterable<Pair<Function<A, B>, A>> l) {
        return mapIterator(l.iterator(), (p) -> p.fst.apply(p.snd));
    }

    public static void main2(String[] args) {
        List<Pair<Consumer<Integer>, Integer>> l = new ArrayList<>();
        forEach(l, (p) -> p.fst.accept(p.snd));
    }

    public static <A> void acceptFuns(Iterable<Pair<Consumer<A>, A>> l) {
        forEach(l, (p) -> p.fst.accept(p.snd));
    }

    public static <A, B> Iterator<Supplier<B>> asyncMapIterator(Iterator<A> it, Function<A, B> f) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Supplier<B> next() {
                final A x = it.next();
                final B[] r = (B[]) new Object[1];
                Thread t = new Thread(() -> { r[0] = f.apply(x); });
                t.start();
                return () -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return r[0];
                };
            }
        };
    }

    public static <T> void asyncForEach(Iterable<T> it, Consumer<T> f) {
        for (T x : it)
            new Thread(() -> f.accept(x)).start();
    }
}

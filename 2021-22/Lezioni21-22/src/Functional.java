import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Functional {

    public interface Function<A, B> {
        B apply(A x);
    }

    public interface Consumer<T> {
        void accept(T x);
    }

    public interface Supplier<T> {
        T get();
    }

    public interface Runnable {
        void run();
    }

    public static <A, B> Collection<B> map(Collection<A> c, Function<A, B> f) {
        Collection<B> r = new ArrayList<>();
        for (A x : c)
            r.add(f.apply(x));
        return r;
    }

    public static <T> void foreach(Collection<T> c, Consumer<T> f) {
        for (T x : c)
            f.accept(x);
    }

    public static <T> Collection<T> generate(int len, Supplier<T> f) {
        Collection<T> r = new ArrayList<>();
        for (; len > 0; --len)
            r.add(f.get());
        return r;
    }

    public static void main(String[] args) {
        List<String> l = List.of("ciao", "come", "ti", "chiami?");

        // test map
        Collection<Integer> r = map(l, (String s) -> s.length());
        Collection<Integer> r2 = map(l, new Function<String, Integer>() {
            @Override
            public Integer apply(String x) {
                return x.length();
            }
        });
        System.out.println(r);
        System.out.println(r2);

        // test foreach
        foreach(l, (String s) -> System.out.println(s));

        // test generate
        Collection<Integer> g1 = generate(10, () -> 7);
        System.out.println(g1);
        Random rand = new Random();
        Collection<Integer> g2 = generate(10, () -> rand.nextInt(100));
        Collection<Integer> g3 = generate(10, new Supplier<Integer>() {
            @Override
            public Integer get() {
                return rand.nextInt(100);
            }
        });
        Collection<Integer> g4 = generate(10, new MySupplier(rand));
        System.out.println(g2);
        System.out.println(g3);
        System.out.println(g4);

        // runnable
        Runnable run1 = () -> System.out.println("ciao");
        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("ciao");
            }
        };
    }

    static class MySupplier implements Supplier<Integer> {

        private final Random rand;

        public MySupplier(Random rand) {
            this.rand = rand;
        }

        @Override
        public Integer get() {
            return rand.nextInt(100);
        }
    }

}

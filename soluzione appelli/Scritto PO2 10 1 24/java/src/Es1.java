import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Es1 {

    // 1.a
    public static class FactorialThread extends Thread {
        private final int n;
        private long res;

        public FactorialThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            res = fact(n);
        }

        public long getResult() {
            try {
                join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return res;
        }

        public int getN() {
            return n;
        }

        private static long fact(int n) {
            if (n <= 1) return 1;
            return n * fact(n - 1);
        }

    }

    // 1.b + 1.c
    public static List<FactorialThread> parallelFactorial(Iterable<Integer> c) {
        List<FactorialThread> r = new ArrayList<>();
        for (int n : c) {
            FactorialThread t = new FactorialThread(n);
            t.start();
            r.add(t);
        }
        return r;
    }

    // 1.d
    public static void main(String[] args) {
        for (FactorialThread t : parallelFactorial(List.of(0, 1, 2, 3, 11, 12, 23, 35)))   // chiamare anche parallelFactorial2() per provarla
            System.out.printf("fact(%d) = %d\n", t.getN(), t.getResult());
    }

    // 1.e.i
    public static <A, B> List<B> map(Iterable<A> i, Function<A, B> f) {
        List<B> r = new ArrayList<>();
        for (A a : i)
            r.add(f.apply(a));
        return r;
    }

    // 1.e.ii
    public static Collection<FactorialThread> parallelFactorial2(Collection<Integer> c) {
        return map(c, (n) -> { FactorialThread t = new FactorialThread(n); t.start(); return t; });
    }
}
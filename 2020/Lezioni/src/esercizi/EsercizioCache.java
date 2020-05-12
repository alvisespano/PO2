package esercizi;

import it.unive.dais.po2.myjdk.Pair;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class EsercizioCache {


    public static class Cache<K, V> {
        @NotNull
        private final Function<K, V> f;
        @NotNull
        private final Map<K, V> m = new HashMap<>();

        public Cache(@NotNull Function<K, V> f) {
            this.f = f;
        }

        @NotNull
        public V lookup(@NotNull K k) {
            if (m.containsKey(k)) {
                return m.get(k);
            } else {
                V v = f.apply(k);
                m.put(k, v);
                return v;
            }
        }
    }


    public static BigInteger fact(long n) {
        return n < 2 ? BigInteger.ONE : fact(n - 1).multiply(new BigInteger(String.valueOf(n)));
    }


    public static long fastFibRecur(long n, Map<Long, Long> cache) {
        return n < 2 ? 1L : fastFib(n - 1, cache) + fastFib(n - 2, cache);
    }

    public static long fastFib(long n, Map<Long, Long> cache) {
        if (cache.containsKey(n)) return cache.get(n);
        else {
            long r = fastFibRecur(n, cache);
            cache.put(n, r);
            return r;
        }
    }

    public static class CachedFib {
        private final Map<Long, Long> cache = new HashMap<>();

        private long fibRecur(long n) {
            return n < 2 ? 1L : fib(n - 1) + fib(n - 2);
        }

        public long fib(long n) {
            if (cache.containsKey(n)) return cache.get(n);
            else {
                long r = fibRecur(n);
                cache.put(n, r);
                return r;
            }
        }
    }

    public static <T> Pair<T, Long> cputime(Supplier<T> f) {
        long now0 = System.nanoTime();
        T r = f.get();
        long now1 = System.nanoTime();
        return new Pair<>(r, now1 - now0);
    }

    public static void main(String[] args) {

        //Cache<Long, Long> cache = new Cache<>((n) -> fastFib(n, cache));

        // versione senza oggetto
        Map<Long, Long> m = new HashMap<>();
        for (int i = 0; i < 100; ++i) {
            long n = i;
            Pair<Long, Long> r = cputime(() -> fastFib(n, m));
            System.out.println(String.format("fib(%d) = %d [%g s]", n, r.first, ((double) r.second / 1000000000.)));
        }

        // versione con oggetto
        CachedFib cache = new CachedFib();
        for (int i = 0; i < 100; ++i) {
            long n = i;
            Pair<Long, Long> r = cputime(() -> cache.fib(n));
            System.out.println(String.format("fib(%d) = %d [%g s]", n, r.first, ((double) r.second / 1000000000.)));
        }


    }

}
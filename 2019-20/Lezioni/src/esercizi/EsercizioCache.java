package esercizi;

import it.unive.dais.po2.myjdk.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class EsercizioCache {


    public static <T> Pair<T, Long> cputime(Supplier<T> f) {
        long now0 = System.nanoTime();
        T r = f.get();
        long now1 = System.nanoTime();
        return new Pair<>(r, now1 - now0);
    }

    // cache funzionale

    public static class FCache<K, V> {
        private final @NotNull BiFunction<FCache<K, V>, K, V> f;
        @NotNull
        private final Map<K, V> m = new HashMap<>();

        public FCache(@NotNull BiFunction<FCache<K, V>, K, V> f) {
            this.f = f;
        }

        @NotNull
        public V lookup(@NotNull K k) {
            if (m.containsKey(k)) {
                V r = m.get(k);
                System.out.println(String.format("hit(%s) = %s", k, r));
                return r;
            } else {
                V v = f.apply(this, k);
                m.put(k, v);
                return v;
            }
        }
    }

    // cache astratta

    public static abstract class AbstractCache<K, V> {
        @NotNull
        private final Map<K, V> m = new HashMap<>();

        protected AbstractCache() {}

        @NotNull
        protected abstract V onMiss(@NotNull K k);

        @NotNull
        protected V recur(@NotNull K k) {
            return lookup(k);
        }

        @NotNull
        public V lookup(@NotNull K k) {
            if (m.containsKey(k)) {
                V r = m.get(k);
                System.out.println(String.format("hit(%s) = %s", k, r));
                return r;
            } else {
                V v = onMiss(k);
                m.put(k, v);
                return v;
            }
        }
    }




    public static void main(String[] args) {
        AbstractCache<Integer, Long> cache1 = new AbstractCache<>() {
            @NotNull
            @Override
            protected Long onMiss(@NotNull Integer n) {
                return n < 2 ? 1 : recur(n - 1) + recur(n - 2);
            }
        };

    }



    // functional version

    public static long cacheFib(FCache<Integer, Long> c, int n) {
        return n < 2 ? 1 : c.lookup(n - 1) + c.lookup(n - 2);
    }

    public static long slowFib(int n) {
        return n < 2 ? 1 : slowFib(n - 1) + slowFib(n - 2);
    }

    public static void main3(String[] args) {
        final int N = 45;

        // slowFib
        for (int i = 0; i < N; ++i) {
            final int n = i;
            Pair<Long, Long> r = cputime(() -> slowFib(n));
            System.out.println(String.format("slowFib(%d) = %d [%g s]", n, r.first, ((double) r.second / 1000000000.)));
        }
        // cacheFib
        FCache<Integer, Long> cache1 = new FCache<>(EsercizioCache::cacheFib);
        for (int i = 0; i < N; ++i) {
            final int n = i;
            Pair<Long, Long> r = cputime(() -> cache1.lookup(n));
            System.out.println(String.format("cacheFib(%d) = %d [%g s]", n, r.first, ((double) r.second / 1000000000.)));
        }
    }


    // versione parametrica

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

    public static void main2(String[] args) {

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
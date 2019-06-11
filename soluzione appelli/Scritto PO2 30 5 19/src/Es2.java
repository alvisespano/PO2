import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Es2 {

    // 2.a
    public static class FiboSequence implements Iterable<Integer> {

        private final int max;
        private final Map<Integer, Integer> cache = new HashMap<>();

        public FiboSequence(int max) {
            this.max = max;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < max;
                }

                @Override
                public Integer next() {
                    return fib(i++);
                }

                private int fib(int n) {
                    if (n < 2) return 1;
                    else {
                        Integer x = cache.get(n);
                        if (x != null) return x;
                        else {
                            int r = fib(n - 1) + fib(n - 2);
                            cache.put(n, r);    // si commenti questo statement per disabilitare la cache e vedere la differenza enorme di tempi di calcolo
                            return r;
                        }
                    }
                }
            };
        }
    }

    // si lanci questo main e si osservi quanto velocemente la macchina produce 100 numeri di fibonacci: se non ci fosse la cache sarebbe drammaticamente più lento a causa delle continue ricorsioni
    public static void main(String[] args) {
        for (int n : new FiboSequence(100)) {
            System.out.println(n);
        }
    }


    // 2.b
    public static void main__desugared() {
        for (Iterator<Integer> it = new FiboSequence(100).iterator(); it.hasNext(); ) { // questo for senza il terzo statement è equivalente ad un while
            int n = it.next();
            System.out.println(n);
        }
    }


    // 2.c
    public static class GlobalFiboSequence implements Iterable<Integer> {

        private final int max;
        private final static Map<Integer, Integer> cache = new HashMap<>(); // è tutto uguale a FiboSequence eccetto per la cache che è statica

        public GlobalFiboSequence(int max) {
            this.max = max;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < max;
                }

                @Override
                public Integer next() {
                    return fib(i++);
                }

                private int fib(int n) {
                    if (n < 2) return 1;
                    else {
                        Integer x = cache.get(n);
                        if (x != null) return x;
                        else {
                            int r = fib(n - 1) + fib(n - 2);
                            cache.put(n, r);    // si commenti questo statement per disabilitare la cache e vedere la differenza enorme di tempi di calcolo
                            return r;
                        }
                    }
                }
            };
        }
    }

}

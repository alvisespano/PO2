import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Es2 {

    // 2.a
    public static class FiboSequence implements Iterable<Integer> {

        private final int len;

        public FiboSequence(int len) {
            this.len = len;
        }

        // questo metodo potrebbe essere static perché non usa nessun membro dell'oggetto
        // tuttavia lo definiamo non-static così possiamo overridarlo nella sottoclasse
        protected int fib(int n) {
            return n < 2 ? 1 : fib(n - 1) + fib(n - 2);
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < len;
                }

                @Override
                public Integer next() {
                    return fib(i++);
                }
            };
        }
    }

    // 2.b
    // anziché definire una classe a sé stante, facciamo una cosa più elegante (sebbene non richiesta dal testo d'esame)
    // definiamo una sottoclasse che overrida solamente il metodo fib() e reimplementa fibonacci con la cache
    // l'iteratore senza saperlo invocherà questo override quando chiama fib()
    public static class CachedFiboSequence extends FiboSequence {

        @NotNull
        private final Map<Integer, Integer> cache;

        // questo costruttore serve alle sottoclasso per passare una certa istanza di cache
        protected CachedFiboSequence(@NotNull Map<Integer, Integer> cache, int len) {
            super(len);
            this.cache = cache;
        }

        // questo costruttore costruisce una cache nuova per ogni istanza
        public CachedFiboSequence(int len) {
            this(new HashMap<>(), len);
        }

        // questo override calcola fibonacci usando la cache ricorsivamente
        @Override
        protected int fib(int n) {
            if (n < 2) return 1;    // non è necessario fare caching anche dei 2 casi base
            else {
                Integer r = cache.get(n);
                if (r == null) {
                    r = fib(n - 1) + fib(n - 2);    // questa è una ricorsione in dynamic dispatch
                    cache.put(n, r);
                }
                return r;
            }
        }
    }

    // 2.c
    public static class GlobalCachedFiboSequence extends CachedFiboSequence {

        // inizializziamo questa cache statica; poi passiamo al supercostruttore sempre questo campo
        // così tutte le istanze useranno sempre la stessa hashmap senza saperlo
        @NotNull
        private final static Map<Integer, Integer> globalCache = new HashMap<>();

        public GlobalCachedFiboSequence(int max) {
            super(globalCache, max);
        }
    }


    /*
     * funzioni di test
     */

    // questo codice non fa parte del tema d'esame, ma per coloro che desiderano testare le 3 differenti
    // versioni di FiboSequence, possono curiosare qui
    private static void execTimed(String text, Runnable r) {
        long t0 = System.nanoTime();
        System.out.printf("[START] %s...\n", text);
        r.run();
        System.out.printf("[DONE] %.3f ms\n", ((double)(System.nanoTime() - t0)) / 1000000.);
    }

    private static void testFibo(FiboSequence seq, int len, int run) {
        execTimed(String.format("%s(%d) #%d", seq.getClass().getName(), len, run),
                () -> {
                    for (int n : seq)
                        System.out.printf("%d ", n);
                    System.out.println();
                });
    }

    private static <Seq extends FiboSequence> void testFibo(Function<Integer, Seq> cons) {
        // parametri modificabili di test
        final List<Integer> LENS = List.of(10, 20, 35, 44);    // lunghezze delle sequenze
        final int REPEATS = 3;                                 // numero di run con la stessa istanza

        // per ogni lunghezza in LENS fa un ciclo di REPEATS ripetizioni
        for (int len : LENS) {
            Seq seq = cons.apply(len);
            // le ripetizioni usano la stessa istanza per verificare la performance della versione cached non-global
            for (int i = 1; i <= REPEATS; ++i)
                testFibo(seq, len, i);
        }
    }

    // si può lanciare questo main e leggere i risultati dei test
    public static void main(String[] args) {
        // i tempi sono lunghi senza cache: più alto è il numero di fibonacci da calcolare, più tempo ci mette
        testFibo(FiboSequence::new);

        // con una cache per ogni istanza i tempi sono enormemente più veloci e non dipende da quanto alto è il numero
        // di fibonacci da calcolare; tuttavia la prima run di ogni ripetizione ci mette leggermente più tempo
        // perché deve popolare la cache per la prima volta
        testFibo(CachedFiboSequence::new);

        // con la cache condivisa raggiungiamo la massima velocità: solamente i numeri di fibonacci mai calcolati finora
        // da qualunque istanza vengono davvero calcolati; quindi è veloce anche la prima run di ogni ciclo
        testFibo(GlobalCachedFiboSequence::new);
    }


}

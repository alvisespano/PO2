import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Random;

public class Es1 {

    // 1.b
    public static class RandomSingleton {
        private RandomSingleton() {
        }

        @Nullable
        private static Random instance;

        @NotNull
        private static Random instance() {
            if (instance == null)
                instance = new Random();
            return instance;
        }

        // fare un setter per cambiare il seed del PRNG è uno dei modi possibili per riprodurre il comportamento originale della classe Random del JDK.
        // normalmente, per avere un seed specifico è necessario costruire un oggetto Random tramite l'apposito costruttore.
        // nella nostra conversione a singleton rappresentiamo invece questo aspetto come un setter che internamente reistanzia il singleton in maniera trasparente all'utente.
        public void setSeed(int seed) {
            instance = new Random(seed);
        }

        public static int nextInt() {
            return instance().nextInt();
        }

        public static boolean nextBoolean() {
            return instance().nextBoolean();
        }

        public static double nextDouble() {
            return instance().nextDouble();
        }

    }

    // 1.c
    public static class RandomIterator implements Iterator<Integer> {
        private int len;

        public RandomIterator(int len) {
            this.len = len;
        }

        @Override
        public boolean hasNext() {
            return len > 0;
        }

        @Override
        public Integer next() {
            --len;
            return RandomSingleton.nextInt();
        }
    }

}

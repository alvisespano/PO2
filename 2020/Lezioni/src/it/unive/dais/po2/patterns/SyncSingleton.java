package it.unive.dais.po2.patterns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SyncSingleton {

    public static class Display {
        // eventuali campi privati non-statici

        @Nullable
        private static Display instance = null;

        private int cnt;

        private Display(int n) {
            this.cnt = n;
            // inizializzazione di tutti i tuoi campi privati non-statici
        }

        // metodi che modificano lo stato
        public synchronized int getCounter() {
            return cnt;
        }

        public synchronized void setCounter(int n) {
            cnt = n;
        }

        public synchronized void incrementCounter() {
            ++cnt;
        }

        @NotNull
        public static synchronized Display getInstance(int cnt) {
            if (instance == null) {
                instance = new Display(cnt);
            }
            synchronized (instance) {
                instance.cnt *= 3;  // esempio di modifica dello stato
            }
            return instance;
        }

    }

    public static void main(String[] args) {
        Display d = Display.getInstance(50);
        Display d2 = Display.getInstance(50);
    }


}

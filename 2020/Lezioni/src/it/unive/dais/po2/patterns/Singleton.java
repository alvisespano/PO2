package it.unive.dais.po2.patterns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Singleton {

    public static class Display {
        // eventuali campi privati non-statici

        @Nullable
        private static Display instance = null;

        private Display() {
            // inizializzazione di tutti i tuoi campi privati non-statici
        }

        @NotNull
        public static Display getInstance() {
            if (instance == null) {
                instance = new Display();
            }
            return instance;
        }

    }

    public static void main(String[] args) {
        Display d = Display.getInstance();
        Display d2 = Display.getInstance();
    }


}

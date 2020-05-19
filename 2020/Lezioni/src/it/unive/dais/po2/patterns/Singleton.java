package it.unive.dais.po2.patterns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// verione minimal di un singleton, ma non è riutilizzabile

public class Singleton {

    @Nullable
    private static Singleton instance = null;

    // fa ciò che fa la cache con una entry
    @NotNull
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }


    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
    }


}

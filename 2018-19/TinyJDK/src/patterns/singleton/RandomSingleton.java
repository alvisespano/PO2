package patterns.singleton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

class RandomSingleton {
    @Nullable
    private static RandomSingleton instance = null;
    @NotNull
    private Random rand;

    // i costruttori sono PRIVATI, per non permettere a nessuno di costruire questa classe senza passare per i metodi statici getInstance()
    private RandomSingleton() {
        rand = new Random();
    }

    // altro costruttore privato con parametro
    private RandomSingleton(int seed) {
        rand = new Random(seed);
    }

    // metodo statico che funge da pseudo-costruttore
    @NotNull
    public static RandomSingleton getInstance() {
        if (instance == null)
            instance = new RandomSingleton();
        return instance;
    }

    // altro metodo statico per avere l'istanza, ma questa volta con un parametro
    @NotNull
    public static RandomSingleton getInstance(int seed) {
        if (instance == null)
            instance = new RandomSingleton(seed);   // qui costruiamo l'oggetto con il costruttore che prende il seed come parametro
        return instance;
    }

}

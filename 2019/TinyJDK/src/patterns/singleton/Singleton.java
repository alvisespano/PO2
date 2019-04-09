package patterns.singleton;

class Singleton {
    private static Singleton instance = null;

    // costruttore privato per non permettere a nessuno di costruire questa classe
    private Singleton() {}

    // metodo statico che funge da pseudo-costruttore
    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }
}

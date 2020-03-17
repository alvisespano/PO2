package it.unive.dais.po2.myjdk;

/**
 * MyList aggiunge a MyCollection i metodi get, add e remove.
 * Questa classe ha metodi che permettono l'accesso con l'indice.
 * la classe MyCollection no.
 * @param <T> Generic type
 */
public interface MyList<T> extends MyCollection<T> {
    T get(int i) throws OutOfBoundsException;
    void add(int i, T x);
    boolean remove(int i);
}

/*
* La differenza tra List e Collection è che la lista permette la get.
* List: una collection che è accessibile con l'indice.
* (In Java, un array è una lista.
* Una lista in Java non è detto che una lista abbia i puntatori ma
* è importante che abbia l'indice di accesso agli elementi).
*/

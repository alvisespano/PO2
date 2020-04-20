package it.unive.dais.po2.myjdk;


// TODO: LinkedSet non è un bel nome, non si capisce: meglio trovarne un'altro

/**
 * Fatto con una linked list.
 * @param <T>
 */

public class MyLinkedSet<T> implements MySet<T> {
    protected MyLinkedList<T> l;

    public MyLinkedSet() {
        this.l = new MyLinkedList<>();
    }

    @Override
    public void add(T x) {
        if (!l.contains(x)) // Controllo se ci sono dupplicati
            l.add(x);
    }

    @Override
    public int size() {
        return l.size();
    } // STUB: un metodo o una funzione non fa altro che chiamarne un'altra che fa la stessa cosa. Bisogna evitare.

    @Override
    public boolean contains(T x) {
        return l.contains(x);
    }

    @Override
    public boolean remove(T x) {
        return l.remove(x);
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public MyIterator<T> iterator() {
        return l.iterator();
    }
}

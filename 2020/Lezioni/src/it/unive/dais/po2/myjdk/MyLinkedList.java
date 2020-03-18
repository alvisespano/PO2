package it.unive.dais.po2.myjdk;

import org.jetbrains.annotations.Nullable;

// TODO: sistemare le possibili eccezioni NullPointerException

/**
 * MyLinkedList è una lista con puntatore al prossimo nodo.
 * @param <T>
 */

public class MyLinkedList<T> implements MyList<T> {

    @Override
    public MyIterator<T> iterator() {
        return null;
    }


    // TODO: come esercizio provare a rendere questa classe statica in modo che abbia il suo generic; e poi modificare MyLinkedList opportunamente

    /**
     * Classe Node
     * usata all'interno di MyLinkedList per implementare i nodi di cui è fatta la lista
     * Anche se non e' una classe parametrica, utilizza il generic T
     * perché ogni nodo della lista ha:
     *  - un campo di tipo T
     *  - un campo di tipo Node che punta all'elemento successivo.
     *
     * Classe Node è una classe nested all'interno della classe MyLinkedList:
     *  - se la classe Node non ha la parola static nella lista,
     *    allora la classe interna vede la classe che la contiene
     *    e tutte le sue caratteristiche (generics, metodi, campi).
     *    Una classe non statica ha bisogno del this.
     *  - una classe interna statica non ha relazione
     *    con la classe che la contiene.
     *
     * Classe protected: chi sta fuori non la può vedere,
     *                   però la potrebbe vedere una sottoclasse.
     */

    protected class Node {
        public T data;
        public Node next;
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // annotation che aiuta il compilatore di Java
    @Nullable
    protected Node head;

    public MyLinkedList() {
        head = null;
    }

    public void add(T e) {
        head = new Node(e, head);
    }

    @Override
    public int size() {
        int r = 0;
        for (Node n = head; n.next != null; ++r);
        /*
        * OPPURE trasformo un for senza corpo che conta in un while ...
        * Node n = head;
        * while(n.next != null)
        *   ++r;
        */
        return r;
    }

    /**
     * Metodo che serve a scorrere la lista
     * @param x è una lista
     * @return true se x e' contenuto nella lista, false altrimenti
     */
    @Override
    public boolean contains(T x) {
        MyIterator<T> it = iterator(); // metodo iterator
        while (it.hasNext()) {
            T e = it.next();
            if (x.equals(e)) return true;
        }
        return false;
    }

    /**
     * Metodo che punta la testa a null.
     * I vecchi nodi della lista li cancellerà il garbage collector in futuro.
     */
    @Override
    public void clear() {
        head = null;
    }

    public T get(int pos) throws OutOfBoundsException {
        Node n = head;
        for (; pos > 0; --pos)

            if ((n = head.next) == null) throw new OutOfBoundsException("get: invalid position " + pos);

            if ((n = head.next) == null)
                throw new OutOfBoundsException("Get: invalid position " + pos);

        return n.data;
    }

    /**
     * Add con indice:
     *  - bisogna scorrere fino all'indice
     *  - aggiungo l'elemento.
     * @param i e' l'indice.
     * @param x e' la lista.
     */
    @Override
    public void add(int i, T x) {
        int r = 0;
        Node n = head;

        for (; n.next != null && r < i; ++r);
        n.next = new Node(x, n.next);

        for (; n.next != null && r < i; ++r); // comincio dalla testa, scorro fino a quando arrivo in posizione 'i'
        // n punta al nodo al quale bisogna aggiungere l'elemento
        n.next = new Node(x, n.next);   // TODO: da testare

    }

    /**
     * Remove con indice:
     *  - bisogna scorrere fino all'indice
     *  - modifico il puntatore dell'elemento all'elemento successivo
     *  - rimuovo l'elemento.
     * @param i e' l'indice.
     */
    @Override
    public boolean remove(int i) {
        int r = 0;
        Node n = head;
        for (; n.next != null && r < i - 1; ++r); // comincio dalla testa, scorro fino a quando arrivo in posizione 'i-1'
        if (n.next != null) {

            n.next = n.next.next;

            // la next dell'elemento precedente punta all'elemento successivo di quello da eliminare, quindi salta un nodo
            n.next = n.next.next;   // TODO: da testare

        }
        return true;
    }

    /**
     * Remove con elemento:
     *  - bisogna scorrere fino all'elemento
     *  - modifico il puntatore dell'elemento all'elemento successivo
     *  - rimuovo l'elemento.
     * @param x elemento da rimuovere.
     */
    @Override
    public boolean remove(T x) {
        // TODO: da fare per casa
        //throw new RuntimeException("not implemented");

        // TODO: implementata
        Node n = head;
        for (; n.next != null && n.next == x; n = n.next); // comincio dalla testa, scorro fino a quando arrivo in posizione 'i-1'
        // la next dell'elemento precedente punta all'elemento successivo di quello da eliminare, quindi salta un nodo
        n.next = n.next.next;
        return true;
    }


}
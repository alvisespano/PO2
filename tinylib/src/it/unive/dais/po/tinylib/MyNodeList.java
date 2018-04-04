package it.unive.dais.po.tinylib;

public class MyNodeList<E> implements MyList<E> {

    private MyNode<E> head;
    private int size;

    public MyNodeList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void insertHead(E data) {
        try {
            insertAt(0, data);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertAt(int position, E data) throws NotFoundException {
        if (position < 0 || position > size())
            throw new NotFoundException("MyNodeList.insertAt(): cannot insert at position " + position);
        if (position == 0)
            head = new MyNode<E>(data, head);
        else {
            MyNode<E> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(new MyNode<E>(data, n.getNext()));
        }
        size++;
    }

    public void removeHead() throws NotFoundException {
        removeAt(0);
    }

    public void removeAt(int position) throws NotFoundException {
        if (position < 0 || position >= size())
            throw new NotFoundException("MyNodeList.removeAt(): cannot remove at position " + position);
        if (position == 0)
            head = head.getNext();
        else {
            MyNode<E> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(n.getNext().getNext());
        }
        size--;
    }

    public int find(E data) throws NotFoundException {
        MyNode<E> n = head;
        int i = 0;
        while (!n.getData().equals(data)) {
            n = n.getNext();
            if (++i > size()) throw new NotFoundException("MyNodeList.find(): cannot find the required element");
        }
        return i;
    }

    public E getHead() throws NotFoundException {
        return getAt(0);
    }

    public E getAt(int position) throws NotFoundException {
        if (position < 0 || position >= size())
            throw new NotFoundException("MyNodeList.getAt(): cannot get element at position " + position);
        if (position == 0)
            return head.getData();
        else {
            MyNode<E> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            return n.getNext().getData();
        }
    }

    // Seguono diverse implementazioni del metodo iterator() che restituisce un iteratore.
    // Ognuna di esse è equivalente alle altre, sono varianti della stessa cosa che usano aspetti diversi del linguaggio per ottenere lo stesso risultato.
    // Definiamo alcuni termini prima di proseguire:
    //    * si dice "nested class" oppure "inner class" (o anche "classe annidata" in italiano) una classe definita DENTRO un'altra, come se fosse un suo membro;
    //    * si dice "enclosing class" la classe che CONTIENE quella annidata;
    //    * si dice "enclosing instance" l'istanza della enclosing class.
    // Nel nostro esempio, MyNodeList (cioè la classe che stiamo definendo ora in questo file) è la enclosing class della inner class MyInnerIterator, definita poco sotto.
    // Per maggiori info consultate il manuale di Java: https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.1.3



    // Il metodo iterator() è quello che bisogna implementare per rispettare l'interfaccia MyIterable.
    // Ma invece di implementare qui le diverse varianti, facciamo uno stub alla vera implementazione che ci interessa tra le varie che abbiamo scritto.
    // Commentate/decommentate le chiamante qui dentro per provare le varie implementazioni diverse.
    @Override
    public MyIterator<E> iterator() {
//        return iterator__anonymous();
//        return iterator__inner();
        return iterator__staticInner();
//        return iterator__external();
    }

    // VARIANTE 1: anonymous object

    public MyIterator<E> iterator__anonymous() {
        return new MyIterator<E>() {    // costruiamo al volo un oggetto anonimo, senza fare una classe
            private int pos = 0;        // non c'è costruttore in un oggetto anonimo, quindi dobbiamo inizializzare i campi direttamente

            @Override
            public boolean hasNext() {
                return pos < size();    // size() è visibile e si può chiamare liberamente perché è un metodo definito dalla enclosing class MyNodeList
            }

            @Override
            public E next() {
                try {
                    return MyNodeList.this.getAt(pos++);    // anche getAt() è visibile, ma scrivo esplicitamente l'espressione dell'oggetto su cui lo invochiamo: MyNodeList.this
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException("MyIterator.next() failed");
                }
            }
        };

    }

    // VARIANTE 2: inner class non statica

    public MyIterator<E> iterator__inner() {
        return new MyInnerIterator();
    }

    // Essendo non-statica, questa nested class vede il generic E della enclosing class e non serve ri-generalizzare.
    private class MyInnerIterator implements MyIterator<E> {
        private int pos = 0;

        // Non servirebbe nessun costruttore qui dentro, perché è sufficiente quello di default (vuoto e senza parametri) generato automaticamente dal compilatore.
        // Ma definendolo noi possiamo specificare private come visibilità, vietando la costruzione a chiunque eccetto che all'enclosing class.
        private MyInnerIterator() {}

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public E next() {
            try {
                return getAt(pos++);
            } catch (NotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("MyIterator.next() failed");
            }
        }
    }

    // VARIANTE 3: inner class statica

    public MyIterator<E> iterator__staticInner() {
        return new MyStaticInnerIterator<E>(this);  // passiamo l'enclosing instance al costruttore e passiamo anche il nostro generic E come type argument
    }

    // Essendo statica, questa inner class non vede il generic E definito dalla enclosing class MyNodeList.
    // Dobbiamo quindi renderla generica a sua volta su un tipo T, perché è come se fosse totalmente slegata dallo scope in cui apparentemente risiede.
    private static class MyStaticInnerIterator<T> implements MyIterator<T> {
        private int pos = 0;
        private MyNodeList<T> enclosing;

        // Occorre definire un costruttore a cui passare l'enclosing instance.
        // Un costruttore privato è invocabile solamente dai metodi della enclosing class, quindi non permettiamo a nessuno di costruire oggetti di questa classe.
        // Il motivo è che vogliamo obbligare l'utente a chiamare il metodo iterator() per avere un iteratore: non deve poterselo costruire esplicitamente.
        private MyStaticInnerIterator(MyNodeList<T> enclosing) {
            this.enclosing = enclosing;
        }

        @Override
        public boolean hasNext() {
            return pos < enclosing.size();
        }

        @Override
        public T next() {
            try {
                return enclosing.getAt(pos++);
            } catch (NotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("MyIterator.next() failed");
            }
        }
    }

    // VARIANTE 4: classe esterna definita in un altro file

    public MyIterator<E> iterator__external() {
        return new MyNodeListIterator<E>(this);
    }

}

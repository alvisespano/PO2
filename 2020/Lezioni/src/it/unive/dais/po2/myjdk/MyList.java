package it.unive.dais.po2.myjdk;

public class MyList<T> {


    private class Node {
        public T data;
        public Node next;
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;

    public MyList() {
        head = null;
    }

    public void add(T e) {
        head = new Node(e, head);
    }

    public T get(int pos) throws OutOfBoundsException {
        Node n = head;
        for (; pos > 0; --pos)
            if ((n = head.next) == null) throw new OutOfBoundsException();
        return n.data;
    }


    public static class NotFoundException extends Exception {

    }

    public static class OutOfBoundsException extends Exception {

    }

    public void remove(int n) throws NotFoundException {
        // TODO
    }

    static void main() {
/*        MyList<Integer> l = new MyList<>();
        l.add(7);
        l.add(67);
        l.add(23);
        l.add(true);
        Integer n = l.get(0);


        MyList<? super Animal> l2 = new MyList<Animal>();
        l2.add(new Animal());
        l2.add(new Dog());
        Animal a = l2.get(1);
    */
    }
}
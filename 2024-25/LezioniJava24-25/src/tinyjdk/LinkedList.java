package tinyjdk;

public class LinkedList<T> implements List<T> {

    protected class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    protected Node head;

    public LinkedList() {
        head = null;
    }

    @Override
    public void add(T e) {
        if (head == null) {
            head = new Node(e);
        }
        else {
            Node n = head;
            while (n.next != null)
                n = n.next;
            n.next = new Node(e);
        }
    }

    protected Node reach(int i) {
        Node n = head;
        for (; n != null && i > 0; --i)
            n = n.next;
        if (i == 0)
            return n;
        throw new RuntimeException("index out of bounds");
    }

    @Override
    public T get(int i) {
        return reach(i).data;
    }

    @Override
    public void set(int i, T e) {
        reach(i).data = e;
    }

    @Override
    public boolean contains(T e) {
        // TODO da fare per casa
        return false;
    }

    @Override
    public int size() {
        // TODO
        return 0;
    }

    @Override
    public void remove(T e) {
        // TODO
    }

    @Override
    public void clear() {
        head = null;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

}

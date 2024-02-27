package tinyjdk;

public class LinkedList<T> implements List<T> {

    private class Node {
        public T data;
        public Node next;
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node head;
    private int sz;
    public LinkedList() {
        this.head = null;
        sz = 0;
    }
    @Override
    public void add(T x) {
        if (head == null) {
            head = new Node(x, null);
        }
        else {
            // TODO da testare
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new Node(x, null);
        }
        ++sz;
    }

    @Override
    public void clear() {
        head = null;
        sz = 0;
    }

    @Override
    public boolean contains(T x) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public T set(int i, T x) {
        return null;
    }

    @Override
    public void add(int i, T x) {

    }

    @Override
    public T remove(int i) {
        return null;
    }
}

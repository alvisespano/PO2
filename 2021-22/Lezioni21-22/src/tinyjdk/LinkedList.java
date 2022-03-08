package tinyjdk;

public class LinkedList<T> implements List<T> {

    protected Node<T> head = null, tail = null;
    protected int len = 0;

    protected static class Node<X> {
        public X data;
        public Node<X> next;
        protected Node(X data, Node<X> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public void add(T e) {
        Node<T> n = new Node<>(e, null);
        if (tail != null) {
            tail.next = n;
            tail = n;
        }
        else
            head = tail = n;
        ++len;
    }

    @Override
    public void remove(T e) throws NotFoundException {

    }

    @Override
    public boolean contains(T e) {
        return false;
    }

    @Override
    public void clear() {
        head = tail = null;
        len = 0;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    protected Node<T> nodeAt(int pos) {
        assert (pos < size());
        Node<T> n = head;
        for (; pos > 0; --pos)
            n = n.next;
        return n;
    }

    @Override
    public T get(int pos) {
        return nodeAt(pos).data;
    }

    @Override
    public void set(int pos, T e) {
        nodeAt(pos).data = e;
    }

    @Override
    public int indexOf(T e) throws NotFoundException {
        return 0;
    }
}

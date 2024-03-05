package tinyjdk;

public class LinkedList<T> implements List<T> {

    protected class Node {
        public T data;
        public Node next;
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    protected Node head;
    protected int sz;
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
    public boolean isEmpty() {
        return head == null;
    }

    // TODO da sistemare
    @Override
    public void remove(T x) {
        Node n = head;
        if (n != null) {
            if (n.data.equals(x)) {
                head = n.next;
                --sz;
            }
            else {
                while (n.next != null) {
                    if (n.next.data.equals(x)) {
                        n.next = n.next.next;
                        --sz;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node n = head;
            @Override
            public boolean hasNext() {
                return n != null;
            }
            @Override
            public T next() {
                T r = n.data;
                n = n.next;
                return r;
            }
        };
    }

    protected Node getNode(int i) {
        if (i < 0 || i >= size())
            throw new RuntimeException(String.format("LinkedList.get(): index %d is out of bound (size = %d)", i, size()));
        Node n = head;
        for (; i > 0; --i)
            n = n.next;
        return n;
    }

    @Override
    public T get(int i) {
        return getNode(i).data;
    }

    @Override
    public T set(int i, T x) {
        Node n = getNode(i);
        T old = n.data;
        n.data = x;
        return old;
    }

    @Override
    public void add(int i, T x) {
        // TODO per casa
    }

    @Override
    public T remove(int i) {
        // TODO per casa
        return null;
    }
}

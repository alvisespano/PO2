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

    // TODO: da testare
    @Override
    public void remove(T e) throws NotFoundException {
        Node<T> prev = null, n = head;
        while (n != null) {
            if (n.data.equals(e)) {
                if (prev != null)
                    prev.next = n.next;
                else
                    head = n.next;
                if (n.next == null)
                    tail = prev;
                return;
            }
            prev = n;
            n = n.next;
        }
        throw new NotFoundException();
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
        return new Iterator<T>() {
            private Node<T> n = head;

            @Override
            public boolean hasNext() {
                return n.next != null;
            }

            @Override
            public T next() {
                T r = n.data;
                n = n.next;
                return r;
            }
        };
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
        Node<T> n = head;
        for (int pos = 0; n != null; ++pos) {
            if (n.data.equals(e)) {
                return pos;
            }
            n = n.next;
        }
        throw new NotFoundException();
    }
}

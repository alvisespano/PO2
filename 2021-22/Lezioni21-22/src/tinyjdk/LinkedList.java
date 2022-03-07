package tinyjdk;

public class LinkedList<T> implements List<T> {

    private Node<T> head;

    // TODO: farla non statica
    private static class Node<X> {
        public X data;
        public Node<X> next;
    }

    @Override
    public void add(T e) {

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

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T get(int pos) {
        return null;
    }

    @Override
    public void set(int pos, T e) {

    }

    @Override
    public int indexOf(T e) throws NotFoundException {
        return 0;
    }
}

package tinyjdk;

public class ArrayListIterator<E> implements Iterator<E> {
    private int pos = 0;
    private ArrayList<E> that;

    public ArrayListIterator(ArrayList<E> that) {
        this.that = that;
    }

    @Override
    public boolean hasNext() {
        return pos < that.sz;
    }

    @Override
    public E next() {
        return that.a[pos++];
    }
}


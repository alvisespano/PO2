package it.unive.dais.po2.aa2020_21.tinyjdk;

public class ArrayListIterator<E> implements Iterator<E> {

    private final ArrayList<E> a;
    private int pos = 0;

    public ArrayListIterator(ArrayList<E> a) {
        this.a = a;
    }

    @Override
    public boolean hasNext() {
        return pos < a.size();
    }

    @Override
    public E next() {
        return a.get(pos++);
    }
}

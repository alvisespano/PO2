package it.unive.dais.po2.tinyjdk;

public class BasicQueue<T> implements Queue<T> {
    private List<T> l = new ArrayList<>();

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public void add(T x) {
        l.add(x);
    }

    @Override
    public void remove(T x) {
        throw new NotImplementedException();
    }

    @Override
    public boolean contains(T x) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void push(T x) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}

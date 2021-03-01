package it.unive.dais.po2.aa2020_21.tinyjdk;

public class ArrayList<T> implements List<T> {
    private Object[] a;
    private int pos;

    public ArrayList() {
        a = new Object[10];
        pos = 0;
    }


    @Override
    public void add(T x) {
        if (pos >= a.length) {
            Object[] newa = new Object[a.length * 2];
            for (int i = 0; i < a.length; ++i)
                newa[i] = a[i];
            a = newa;
        }
        a[pos++] = x;
    }

    @Override
    public boolean contains(T x) {
        for (Object o_ : a) {
            T o = (T) o_;
            if (o.equals(x)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return pos;
    }

    @Override
    public void remove(T x) {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T get(int index) {
        checkPos(index);
        return (T) a[index];
    }

    @Override
    public void set(int index, T x) {
        checkPos(index);
        a[index] = x;
    }

    private void checkPos(int index) {
        if (index < 0 || index >= size()) throw new RuntimeException(String.format("index %d is greater than size %d", index, size()));
    }
}

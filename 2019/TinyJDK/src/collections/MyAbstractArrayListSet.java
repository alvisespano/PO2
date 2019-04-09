package collections;

import java.util.*;
import java.util.function.Function;

public abstract class MyAbstractArrayListSet<T> implements MySet<T> {
    protected final ArrayList<T> a;

    protected MyAbstractArrayListSet() {
        this.a = new ArrayList<T>();
    }

    @Override
    public void add(T x) {
        if (!contains(x)) {
            a.add(x);
            sort();
        }
    }

    protected abstract void sort();

    @Override
    public void clear() {
        a.clear();
    }

    @Override
    public void remove(T x) {
        a.remove(x);
    }

    @Override
    public boolean contains(T x) {
        return a.contains(x);
    }

    @Override
    public boolean contains(Function<T, Boolean> p) {
        return a.contains(p);
    }

    @Override
    public int size() {
        return a.size();
    }

    @Override
    public MyIterator<T> iterator() {
        Iterator<T> it = a.iterator();
        return new MyIterator<T>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public T next() {
                return it.next();
            }
        };
    }

    @Override
    public int find(T x) throws Exception {
        int r = a.indexOf(x);
        if (r < 0) throw new Exception("not found");
        return r;
    }
}

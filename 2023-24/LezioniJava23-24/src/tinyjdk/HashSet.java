package tinyjdk;

public class HashSet<T> extends AbstractResizableArray<T> implements Set<T>{

    @Override
    public void add(T x) {
        if (!contains(x))
            super.add(x);
    }
    @Override
    public boolean contains(T x) {
        Iterator<T> it = iterator();
        int h = x.hashCode();
        while (it.hasNext()) {
            T e = it.next();
            if (e.hashCode() == h)
                return true;
        }
        return false;
    }
    @Override
    public void remove(T x) {
        // TODO
    }

    @Override
    public Iterator<T> iterator() {
        // TODO
        return null;
    }
}

package tinyjdk;
public class StructuralSet<T> implements Set<T> {
    private List<T> l = new ArrayList<>();
    @Override
    public void add(T x) {
        if (!l.contains(x))
            l.add(x);
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public boolean isEmpty() {
        return l.isEmpty();
    }

    @Override
    public void remove(T x) {
        l.remove(x);
    }

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public Iterator<T> iterator() {
        return l.iterator();
    }
}

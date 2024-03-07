package tinyjdk;
public class StructuralSet<T>
        extends AbstractResizableArray<T>
        implements Set<T> {
    @Override
    public void add(T x) {
        if (!contains(x))
            super.add(x);
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

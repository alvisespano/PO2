package tinyjdk;

public interface Collection<X> extends Iterable<X> {
    void add(X e);
    void remove(X e) throws NotFoundException;
    boolean contains(X e);
    void clear();
    int size();
}

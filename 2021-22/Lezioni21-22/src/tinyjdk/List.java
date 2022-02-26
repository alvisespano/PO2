package tinyjdk;

public interface List<X> extends Collection<X> {
    X get(int pos);
    void set(int pos, X e);
    int indexOf(X e) throws NotFoundException;
}

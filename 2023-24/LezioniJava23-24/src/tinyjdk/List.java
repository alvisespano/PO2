package tinyjdk;

public interface List<T> extends Collection<T> {
    T get(int i);
    T set(int i, T x);
    void add(int i, T x);
    T remove(int i);
}

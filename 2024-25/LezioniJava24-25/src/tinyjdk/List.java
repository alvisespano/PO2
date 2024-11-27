package tinyjdk;

public interface List<T> extends Collection<T> {
    T get(int i);
    void set(int i, T e);
}

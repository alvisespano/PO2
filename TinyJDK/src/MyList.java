

public interface MyList<T> extends MyCollection<T> {
    void add(int i, T x);
    T get(int i);
    void set(int i, T x);
}

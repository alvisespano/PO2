package collections;


public interface MyCollection<T> {

    void add(T o);
    void remove(T o);
    MyIterator<T> getIterator();
}

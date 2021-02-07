package collections;

public interface MyIterable<E> {

    MyIterator<E> iterator();
    int find(E x) throws Exception;

}

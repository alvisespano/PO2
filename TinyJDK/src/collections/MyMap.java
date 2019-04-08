package collections;

public interface MyMap<K, V> extends MyCollection<Pair<K, V>> {

    void put(K key, V value);
    V get(K key) throws NotFoundException;

}

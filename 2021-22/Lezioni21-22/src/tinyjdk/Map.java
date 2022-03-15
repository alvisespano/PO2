package tinyjdk;

public interface Map<K, V> extends Iterable<Pair<K, V>> {
    V get(K key);
    V put(K key, V value);
    void clear();
    boolean containsKey(K key);
    boolean containsValue(V value);
    void remove(K key) throws NotFoundException;
}

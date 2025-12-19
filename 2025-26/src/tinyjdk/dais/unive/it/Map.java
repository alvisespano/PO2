package tinyjdk.dais.unive.it;

public interface Map<K, V> extends Iterable<Map.Pair<K, V>> {

    class Pair<K, V> {
        public final K key;
        public final V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    void put(K key, V value);
    V get(K key);
    int size();

    default boolean isEmpty() { return size() == 0; }

    boolean containsKey(K key);
}

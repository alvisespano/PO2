package it.unive.dais.po2.tinyjdk;

public interface Map<K, V> extends Iterable<Pair<K, V>> {
    class KeyNotFoundException extends Exception {
        public KeyNotFoundException(String msg) {
            super(msg);
        }
    }

    void put(K key, V value);
    V get(K key) throws KeyNotFoundException;

    boolean containsKey(K key);
    V removeKey(K key) throws KeyNotFoundException;
}

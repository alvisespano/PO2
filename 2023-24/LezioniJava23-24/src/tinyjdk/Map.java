package tinyjdk;
import java.lang.Exception;

public interface Map<K, V> extends Iterable<Pair<K, V>> {
    void put(K k, V v);

    V get(K k) throws KeyNotFoundException;

    class KeyNotFoundException extends Exception {
        public KeyNotFoundException(Object k) {
            super(String.format("key %s not found in map", k));
        }
    }
}


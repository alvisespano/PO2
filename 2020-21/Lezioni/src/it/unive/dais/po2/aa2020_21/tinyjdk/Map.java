package it.unive.dais.po2.aa2020_21.tinyjdk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Map<K, V> extends Iterable<Pair<K, V>> {
    void put(@NotNull K key, @Nullable V value);
    @Nullable V get(@NotNull K key) throws KeyNotFoundException;
    int size();
    void remove(@NotNull K key);

    class KeyNotFoundException extends Exception {
        public KeyNotFoundException(Object k) {
            super(String.format("key = %s", k));
        }
    }

}

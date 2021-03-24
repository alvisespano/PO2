package it.unive.dais.po2.aa2020_21.tinyjdk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Map<K, V> extends Iterable<Pair<K, V>> {
    void put(@NotNull K key, @Nullable V value);
    @Nullable V get(@NotNull K key) throws NotFoundException;
    int size();
    void remove(@NotNull K key);

    class NotFoundException extends Exception {
        public NotFoundException(Object k) {
            super(String.format("key = %s", k));
        }
    }

}

package it.unive.dais.po2.aa2020_21.tinyjdk;

public interface Map<K, V> extends Iterable<Pair<K, V>> {
    void put(K key, V value);
    V get(K key) throws PairMap.NotFoundException;
    int size();
    void remove(K key);
    Iterator<Pair<K, V>> iterator();
}

package it.unive.dais.po2.myjdk;

public interface MyMap<K, V> {
    V get(K k) throws NotFoundException;
    void put(K k, V v);
    void clear();
}

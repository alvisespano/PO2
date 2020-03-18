package it.unive.dais.po2.myjdk;

/**
 * L'interfaccia MyMap definisce mappe generali.
 * Si possono fare le seguenti operazioni:
 *      - get
 *      - put
 *      - clear
 * Il concetto di mappa è rappresentato da un interfaccia
 * @param <K> key [valore generale]
 * @param <V> value
 */
public interface MyMap<K, V> {
    /**
     * Metodo per fare la lookup della mappa
     * [data una chiave restituisce un valore].
     * @param k
     * @return value
     * @throws NotFoundException
     */
    V get(K k) throws NotFoundException;

    /**
     * Metodo che data una chiave e un valore li inserisce nella mappa.
     * @param k
     * @param v
     */
    void put(K k, V v);



    /*
    * Metodo che data una chiave e un valore li inserisce in una coppia
    * Se nella firma ci fosse 'estends MyCollections<Pair(K, V)>'
    * default void add(K k, V v) {
    *   add(new Pair<>(k, v)); // add ereditata da collection
    * }
    */


    void clear();
}

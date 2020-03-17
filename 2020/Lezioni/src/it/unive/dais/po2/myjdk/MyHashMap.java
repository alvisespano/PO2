package it.unive.dais.po2.myjdk;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private MyArrayList<Pair<K, V>> m; // m: lista associativa
    /*
    * Usiamo un ArrayList e non un Array
    * perché ArrayList è facile da estendere
    */

    /**
     * Metodo che restituisce il valore data la chiave
     * @param k key
     * @return value
     * @throws NotFoundException
     */
    @Override
    public V get(K k) throws NotFoundException {
        MyIterator<Pair<K, V>> it = m.iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            // per ogni coppia se il primo elemento è uguale alla chiave ritorno il secondo
            if (p.first.equals(k)) return p.second;
        }
        // se finisco il while senza ritornare nulla, allora lancio un'eccezione
        throw new NotFoundException();
    }

    /**
     * Aggiunge in fondo alla lista una coppia.
     * @param k
     * @param v
     */
    @Override
    public void put(K k, V v) {
        /*
        * Implementazione del controllo utile quando
        * qualcuno fa la put di una chiave k che c'è già.
        *
        * Strategia: SOSTITUZIONE
        *            se la chiave associata ad un valore all'interno della mappa esiste già,
        *            allora si sostituisce il valore,
        *            così non si hanno mai le coppie con lo stesso primo elemento.
        * Una proprietà simile ce l'ha l'HashSet (che non può avere elementi uguali per proprietà),
        * ma se qualcuno cerca di fare una put con chiave uguale e valore diverso,
        * l'HashSet non cattura quest'informazione, quindi non è ciò che serve in questo caso.
        *
        *
        */
        MyIterator<Pair<K, V>> it = m.iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            // Se troviamo che c'è già la chiave
            if (p.first.equals(k)) {
                // sostituiamo il valore esistente con quello nuovo
                p.second = v;
                return;
            }
            // TODO: fare la stessa cosa con le coppie immutabili
        }
        // operazione che aggiunge la chiave
        m.add(new Pair<>(k, v));
    }
}

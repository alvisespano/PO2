package it.unive.dais.po2.tinyjdk;

public class PairMap<K, V> implements Map<K, V> {
    private List<Pair<K, V>> l = new ArrayList<>();

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return l.iterator();
    }

    @Override
    public void put(K key, V value) {
        l.add(new Pair<K, V>(key, value));
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        Iterator<Pair<K, V>> it = l.iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (p.fst.equals(key))
                return p.snd;
        }
        throw new KeyNotFoundException(String.format("key %s not found", key));
    }

    @Override
    public boolean containsKey(K key) {
        try {
            get(key);
            return true;
        }
        catch (KeyNotFoundException e) {
            return false;
        }
    }

    @Override
    public V removeKey(K key) throws KeyNotFoundException {
        // TODO per voi
        throw new NotImplementedException();
    }
}

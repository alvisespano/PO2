package it.unive.dais.po2.tinyjdk;

public class HashMap<K, V> implements Map<K, V> {
    private static int CAPACITY = 1000;

    private List<List<Pair<K, V>>> l = new ArrayList<>(CAPACITY);


    @Override
    public Iterator<Pair<K, V>> iterator() {
        return null;
    }

    @Override
    public void put(K key, V value) {
        int h = key.hashCode() % CAPACITY;
        List<Pair<K, V>> inl = l.get(h);
        if (inl == null) {
            inl = new ArrayList<>();
            l.set(h, inl);
        }
        inl.add(new Pair<>((key, value));
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        int h = key.hashCode() % CAPACITY;
        List<Pair<K, V>> inl = l.get(h);
        if (inl == null)
            throw new KeyNotFoundException(String.format("key %s is missing", key));
        for (int i = 0; i < inl.size(); ++i) {
            Pair<K, V> p = inl.get(i);
            if (p.fst.equals(key))
                return p.snd;
        }
        throw new RuntimeException(String.format("unexpected error: key %s is not found at index %d", key, h));
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V removeKey(K key) throws KeyNotFoundException {
        return null;
    }
}

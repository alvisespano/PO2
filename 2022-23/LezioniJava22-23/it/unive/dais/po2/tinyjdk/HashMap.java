package it.unive.dais.po2.tinyjdk;

public class HashMap<K, V> implements Map<K, V> {
    private static int CAPACITY = 1000;

    private List<List<Pair<K, V>>> l = new ArrayList<>(CAPACITY);

    @Override
    public Iterator<Pair<K, V>> iterator() {
        // TODO fixare o riscrivere per casa
        return new Iterator<Pair<K, V>>() {
            private int i = 0, j = -1;
            private boolean _hasNext = true;

            @Override
            public boolean hasNext() {
                return _hasNext;
            }

            @Override
            public Pair<K, V> next() {
                Pair<K, V> r = null;
                if (j >= 0) {
                    List<Pair<K, V>> inl = l.get(i);
                    r = inl.get(j++);
                    if (j > inl.size())
                        j = -1;
                }
                else {
                    for (; i < l.size(); ++i) {
                        List<Pair<K, V>> inl = l.get(i);
                        if (inl == null)
                            continue;
                        r = inl.get(0);
                        j = 1;
                    }
                }
                if (r == null)
                    _hasNext = false;
                return r;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        int h = key.hashCode() % CAPACITY;
        List<Pair<K, V>> inl = l.get(h);
        if (inl == null) {
            inl = new ArrayList<>();
            l.set(h, inl);
        }
        inl.add(new Pair<>(key, value));
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        int h = key.hashCode() % CAPACITY;
        List<Pair<K, V>> inl = l.get(h);
        if (inl != null) {
            for (int i = 0; i < inl.size(); ++i) {
                Pair<K, V> p = inl.get(i);
                if (p.fst.equals(key))
                    return p.snd;
            }
        }
        throw new KeyNotFoundException(String.format("key %s is missing", key));
//        throw new RuntimeException(String.format("unexpected error: key %s is not found at index %d", key, h));
    }

    @Override
    public boolean containsKey(K key) {
        // TODO per casa
        return false;
    }

    @Override
    public V removeKey(K key) throws KeyNotFoundException {
        // TODO per casa
        return null;
    }
}

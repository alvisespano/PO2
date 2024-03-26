package tinyjdk;

public class PairMap<K, V> implements Map<K, V> {
    private final List<Pair<K, V>> l = new ArrayList<>();

    @Override
    public void put(K k, V v) {
        assert (k != null);
        l.add(new Pair<>(k, v));
    }

    @Override
    public V get(K k) throws KeyNotFoundException {
        for (int i = l.size() - 1; i >= 0 ; --i) {
            final Pair<K, V> p = l.get(i);
            if (p.first.equals(k))
                return p.second;
        }
        throw new KeyNotFoundException(k);
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        Set<K> history = new HashSet<>();
        List<Pair<K, V>> r = new ArrayList<>();
        for (int i = l.size() - 1; i >= 0; --i) {
            Pair<K, V> p = l.get(i);
            if (!history.contains(p.first)) {
                history.add(p.first);
                r.add(p);
            }
        }
        return r.iterator();
    }
}

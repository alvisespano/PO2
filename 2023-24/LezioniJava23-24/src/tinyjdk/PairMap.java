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
        return new Iterator<>() {
            private int pos = l.size() - 1;
            private Set<K> history = new HashSet<>();
            @Override
            public boolean hasNext() {
                return false;
            }
            @Override
            public Pair<K, V> next() {
                Pair<K, V> r = l.get(pos--);
                K k = r.first;
                if (history.contains(k)) {
                    if (hasNext()) return next();
                }
                else {
                    history.add(k);
                    return r;
                }
            }
        };
    }
    public static void main(String[] args) {
        Map<Integer, String> m = new PairMap<>();
        m.put(5, "ciao");
        m.put(678, "ciccio");
        m.put(24, "franco");
        m.put(98, "pippo");
        m.put(5, "baudo");
        for (Pair<Integer, String> p : m) {

        }
    }
}

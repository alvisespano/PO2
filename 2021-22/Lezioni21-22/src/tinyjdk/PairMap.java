package tinyjdk;

public class PairMap<K, V> implements Map<K, V> {
    private List<Pair<K, V>> l;

    public PairMap() {
        l = new ArrayList<>();
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return l.iterator();
    }

    @Override
    public V get(K key) {
        Pair<K, V> p = find(key);
        if (p != null) return p.getSecond();
        return null;
    }

    private Pair<K, V> find(K key) {
        Iterator<Pair<K, V>> it = this.iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (p.getFirst().equals(key))
                return p;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Pair<K, V> p = find(key);
        if (p != null) {
            V r = p.getSecond();
            p.setSecond(value);
            return r;
        }
        l.add(new Pair<>(key, value));
        return null;
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Iterator<Pair<K, V>> it = this.iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (p.getSecond().equals(value))
                return true;
        }
        return false;
    }

    @Override
    public void remove(K key) throws NotFoundException {
        l.remove(find(key));
    }
}

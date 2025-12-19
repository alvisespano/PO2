package tinyjdk.dais.unive.it;

public class ListMap<K, V> implements Map<K, V> {
    private List<Map.Pair<K, V>> l;

    public ListMap() {
        l = new ArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        l.add(new Map.Pair<>(key, value));
    }

    @Override
    public V get(K key) {
        Iterator<Map.Pair<K, V>> it = l.iterator();
        while (it.hasNext()) {
            Map.Pair<K, V> p = it.next();
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        throw new RuntimeException("key not found");
    }

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public boolean containsKey(K key) {
        Iterator<Map.Pair<K, V>> it = l.iterator();
        while (it.hasNext()) {
            Map.Pair<K, V> p = it.next();
            if (p.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return l.iterator();
    }

}

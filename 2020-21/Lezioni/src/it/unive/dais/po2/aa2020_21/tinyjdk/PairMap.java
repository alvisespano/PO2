package it.unive.dais.po2.aa2020_21.tinyjdk;

import org.jetbrains.annotations.NotNull;

public class PairMap<K, V> implements Map<K, V> {
    private final ArrayList<Pair<K, V>> a = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < a.size(); ++i) {
            K e = a.get(i).first;
            if (e.equals(key)) {
                a.set(i, new Pair<>(e, value));
                return;
            }
        }
        a.add(new Pair<>(key, value));
    }

    @Override
    @NotNull    // TODO annotare tutti metodi
    public V get(K key) throws NotFoundException {
        Iterator<Pair<K, V>> it = iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (p.first.equals(key)) return p.second;
        }
        throw new NotFoundException(key);
    }

    @Override
    public int size() {
        return 0;   // TODO
    }

    @Override
    public void remove(K key) {
        // TODO
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return null; // TODO
    }

    public static class NotFoundException extends Exception {
        public NotFoundException(Object k) {
            super(String.format("key = %s", k));
        }
    }
}

package it.unive.dais.po2.aa2020_21.tinyjdk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PairMap<K, V> implements Map<K, V> {
    @NotNull
    private final ArrayList<Pair<K, V>> a = new ArrayList<>();

    @Nullable
    private Pair<Integer, Pair<K, V>> search(@NotNull K key) {
        for (int i = 0; i < a.size(); ++i) {
            @NotNull final Pair<K, V> p = a.get(i);
            K e = p.first;
            if (e.equals(key)) {
                return new Pair<>(i, p);
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull K key, @Nullable V value) {
        Pair<Integer, Pair<K, V>> r = search(key);
        if (r != null) {
            a.set(r.first, new Pair<>(r.second.first, value));
        }
        else
            a.add(new Pair<>(key, value));
    }

    @Override
    public V get(@NotNull K key) throws NotFoundException {
        Iterator<Pair<K, V>> it = iterator();   // TODO usare search()
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (p.first.equals(key)) return p.second;
        }
        throw new NotFoundException(key);
    }

    @Override
    public int size() {
        return a.size();
    }

    @Override
    public void remove(@NotNull K key) {
        Pair<Integer, Pair<K, V>> r = search(key);
        if (r != null) {
            a.remove(r.first);
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return a.iterator();
    }

    @Nullable
    public static class NotFoundException extends Exception {
        public NotFoundException(Object k) {
            super(String.format("key = %s", k));
        }
    }

}

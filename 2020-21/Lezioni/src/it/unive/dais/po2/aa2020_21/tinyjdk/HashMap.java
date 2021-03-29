package it.unive.dais.po2.aa2020_21.tinyjdk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;


public class HashMap<K, V> implements Map<K, V> {

    @NotNull
    private final ArrayList<LinkedList<Pair<K, V>>> a;


    public HashMap(int capacity) {
        this.a = new ArrayList<>(capacity);
        for (int i = 0; i < a.size(); ++i) {
            a.set(i, new LinkedList<>());
        }
    }

    private int indexOf(@NotNull K key) {
        return key.hashCode() % a.size();
    }

    @Override
    public void put(@NotNull K key, @Nullable V value) {
        int i = indexOf(key);
        LinkedList<Pair<K, V>> l = a.get(i);
        l.add(new Pair<>(key, value));
    }

    @Override
    public @Nullable V get(@NotNull K key) throws KeyNotFoundException {
        LinkedList<Pair<K, V>> l = a.get(indexOf(key));
        for (Pair<K, V> p : l) {
            if (p.first.equals(key))
                return p.second;
        }
        throw new KeyNotFoundException("HashMap.get()");
    }

    @Override
    public int size() {
        int r = 0;
        Iterator<LinkedList<Pair<K, V>>> it = a.iterator();
        while (it.hasNext()) {
            LinkedList<Pair<K, V>> l = it.next();
            r += l.size();
        }
        return r;
    }


    @Override
    public Iterator<Pair<K, V>> iterator() {    // TODO da testare
        // versione alternativa con lista d'appoggio
//        List<Pair<K, V>> r = new ArrayList<Pair<K, V>>();
//        for (LinkedList<Pair<K, V>> l : a)
//            for (Pair<K, V> p : l)
//                r.add(p);
//        return r.iterator();

        return new Iterator<Pair<K, V>>() {
            private int i = 0, j = 0;

            @Override
            public boolean hasNext() {
                return i < a.size();    // TODO da pensare se è sufficiente così o se serve j < a.get(i).size() ||
            }

            @Override
            public Pair<K, V> next() {
                LinkedList<Pair<K, V>> p = a.get(i);
                Pair<K, V> r = p.get(j++);
                if (j >= p.size()) {
                    ++i;
                    j = 0;
                }
                return r;
            }
        };
    }

    @Override
    public void remove(@NotNull K key) {

    }
}

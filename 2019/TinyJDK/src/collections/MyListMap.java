package collections;

public class MyListMap<K, V> extends MyArrayList<Pair<K, V>>
        implements MyMap<K, V> {

    @Override
    public void put(K key, V value) {
        add(new Pair<K, V>(key, value));
    }

    @Override
    public V get(K key) throws NotFoundException {
        MyIterator<Pair<K, V>> it = iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (key.equals(p.getFirst())) return p.getSecond();
        }
        throw new NotFoundException();
    }

}


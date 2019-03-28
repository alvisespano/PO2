import java.util.function.Function;

public class MyListMap__old<K, V> implements MyMap<K, V> {

    private MyList<Pair<K, V>> l;

    public MyListMap__old() {
        this.l = new MyArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        l.add(new Pair<K, V>(key, value));
    }

    @Override
    public V get(K key) throws NotFoundException {
        MyIterator<Pair<K, V>> it = l.iterator();
        while (it.hasNext()) {
            Pair<K, V> p = it.next();
            if (key.equals(p.getFirst())) return p.getSecond();
        }
        throw new NotFoundException();
    }

    @Override
    public void add(Pair<K, V> x) {
        put(x.getFirst(), x.getSecond());
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public void remove(Pair<K, V> x) {
        l.remove(x);
    }

    @Override
    public boolean contains(Pair<K, V> x) {
        return l.contains(x);
    }

    @Override
    public boolean contains(Function<Pair<K, V>, Boolean> p) {
        return l.contains(p);
    }

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public MyIterator<Pair<K, V>> iterator() {
        return l.iterator();
    }

    @Override
    public int find(Pair<K, V> x) throws Exception {
        return l.find(x);
    }
}

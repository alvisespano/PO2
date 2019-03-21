import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;

public class MyArrayListSet<T extends Comparable<T>> implements MySet<T> {
    private final Comparator<T> p;
    private final ArrayList<T> a;

    public MyArrayListSet(Comparator<T> p) {
        this.a = new ArrayList<T>();
        this.p = p;
    }

    @Override
    public void add(T x) {
        if (!contains(x)) {
            a.add(x);
            sort();
        }
    }

    private void sort() {
        Collections.sort(a, p);
    }

    @Override
    public void clear() {
        a.clear();
    }

    @Override
    public void remove(T x) {
        a.remove(x);
    }

    @Override
    public boolean contains(T x) {
        return a.contains(x);
    }

    @Override
    public boolean contains(Function<T, Boolean> p) {
        return a.contains(p);
    }

    @Override
    public int size() {
        return a.size();
    }

    @Override
    public MyIterator<T> iterator() {
        return a.iterator();
    }

    @Override
    public int find(T x) throws Exception {
        return a.find(x);
    }
}

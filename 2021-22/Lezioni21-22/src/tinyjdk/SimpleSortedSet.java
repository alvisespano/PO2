package tinyjdk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class SimpleSortedSet<T extends Comparable<? super T>> implements SortedSet<T> {
    private List<T> l = new ArrayList<>();

    @Override
    public void add(T e) {
        if (!contains(e)) {
            l.add(e);
            Collections.sort(l);
        }
    }

    @Override
    public void remove(T e) throws NotFoundException {

    }

    @Override
    public boolean contains(T e) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

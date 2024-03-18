package tinyjdk;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class StructuralSortedSet<T extends Comparable<T>>
        extends StructuralSet<T>
        implements SortedSet<T> {

    @Override
    public void add(T x) {
        super.add(x);
        sort();
    }

/*    private void sort() {
        T[] a = (T[]) this.a;
        while (...) {
            int i = 0;
            if (a[i].compareTo(a[i + 1]) > 0) {
                T tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
            }
        }
    }*/

    private void sort() {
        T[] src = (T[]) a;
        Arrays.sort(src, 0, size(), new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Override
    public T first() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) a[0];
    }

    @Override
    public T last() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) a[size() - 1];
    }
}

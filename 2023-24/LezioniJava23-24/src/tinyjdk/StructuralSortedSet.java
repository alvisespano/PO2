package tinyjdk;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class StructuralSortedSet<T extends Comparable<T>>
        extends StructuralSet<T>
        implements SortedSet<T> {

    @Override
    public void add(T x) {
        super.add(x);
        sort();
    }

    // static <T extends Comparable<T>> void sort(List<T> l);
    //static <T> void sort(T[] a, Comparator<? super T> c) {}
    private void sort() {
        T[] src = (T[]) a;
        T[] tmp = Arrays.copyOf(src, size());
        Arrays.sort(tmp, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });

        // TODO ricopiare tmp in a

    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }
}

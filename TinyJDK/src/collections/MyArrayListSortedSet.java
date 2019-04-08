package collections;

import java.util.*;

public class MyArrayListSortedSet<T extends Comparable<T>>
        extends MyAbstractArrayListSet<T>
        implements MySortedSet<T> {

    public MyArrayListSortedSet() {
        super();
    }

    @Override
    protected void sort() {
        Collections.sort(a);
    }

}

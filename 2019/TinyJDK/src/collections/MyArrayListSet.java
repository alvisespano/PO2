package collections;

import java.util.*;

public class MyArrayListSet<T> extends MyAbstractArrayListSet<T> {

    private final Comparator<T> p;

    public MyArrayListSet(Comparator<T> p) {
        super();
        this.p = p;
    }

    @Override
    protected void sort() {
        Collections.sort(a, p);
    }

}

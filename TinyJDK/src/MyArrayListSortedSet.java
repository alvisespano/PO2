import java.util.*;
import java.util.function.Function;

public class MyArrayListSortedSet<T extends Comparable<T>>
        extends MyAbstractArrayListSet<T> {

    public MyArrayListSortedSet() {
        super();
    }

    @Override
    protected void sort() {
        Collections.sort(a);
    }

}

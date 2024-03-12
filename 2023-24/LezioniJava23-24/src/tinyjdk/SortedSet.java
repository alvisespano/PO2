package tinyjdk;

public interface SortedSet<T extends Comparable<T>> extends Set<T> {
    T first();
    T last();
}

package collections;

import java.util.function.Function;

public interface MyCollection<T> extends MyIterable<T> {
    void add(T x);
    void clear();
    void remove(T x);   // da decidere se ci piace o no
    boolean contains(T x);
    boolean contains(Function<T, Boolean> p);
    int size();


}

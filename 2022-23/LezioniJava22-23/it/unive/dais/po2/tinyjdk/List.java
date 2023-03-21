package it.unive.dais.po2.tinyjdk;
import java.util.function.Predicate;

public interface List<T> extends Collection<T> {
    T get(int index);

    void set(int index, T x);

    void removeAt(int index);

    default T removeIf(Predicate<T> p) {
        for (int i = 0; i < size(); ++i) {
            T x = get(i);
            if (p.test(x)) {
                removeAt(i);
                return x;
            }
        }
        return null;
    }

}

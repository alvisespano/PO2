
// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 1/7/2022 per ciò che riguarda i quesiti 1-2, ovvero le domande che coinvolgono Java.
// Il quesito 3 riguardante C++ è in un progetto Visual Studio a parte, non qui.
// Il codice qui esposto è Java 8+.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;

public class Es1 {

    // 1.a
    public interface Predicate<T> extends Function<T, Boolean> {}

    // 1.b
    public interface Either<T> {
        T onSuccess(T x);
        void onFailure(T x) throws Exception;
    }

    // 1.c
    public static class SkippableArrayList<E> extends ArrayList<E> {
        public Iterator<E> iterator(Predicate<E> p, Either<E> f) {
            final Iterator<E> it = super.iterator();    // può anche essere un campo privato della anonymous class
            return new Iterator<E>() {
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public E next() {
                    E x = it.next();
                    if (p.apply(x))
                        return f.onSuccess(x);
                    else {
                        try {
                            f.onFailure(x);
                        }
                        catch (Exception e) {
                            e.printStackTrace();    // si può anche non fare niente dentro il catch, è indifferente
                        }
                        return x;
                    }
                }
            };
        }
    }

}

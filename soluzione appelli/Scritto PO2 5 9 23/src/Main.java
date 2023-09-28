import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    // 1.a
    public static <A, B> Iterator<Supplier<B>> asyncIterator(Iterator<A> it, Function<A, B> f) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            private class Future implements Supplier<B> {
                @Nullable
                private B r;
                @NotNull
                private final Thread t;

                public Future(Supplier<B> f) {
                    t = new Thread(() -> { r = f.get(); });
                }

                @Override
                @NotNull
                public B get() {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return r;
                }
            }

            @Override
            public Supplier<B> next() {
                A a = it.next();
                return new Future(() -> f.apply(a));
            }
        };
    }

    // 1.b
    static <T extends Comparable<? super T>> void sortLists(Iterable<List<T>> c) {
        Iterator<Supplier<List<T>>> it = asyncIterator(c.iterator(), (l) -> { Collections.sort(l); return l; });
        while (it.hasNext()) {
            Supplier<List<T>> f = it.next();
            System.out.println(f.get());    // questo non è davvero necessario, ma
        }
    }

}
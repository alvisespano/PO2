package it.unive.dais.po;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Es3 {

    // 3.a
    @FunctionalInterface
    public interface Predicate<T> {
        boolean apply(T x);
    }

    // 3.b
    public interface Either<T> {
        T onSuccess(T x);
        void onFailure(T x) throws Exception;
    }

    // 3.c
    public static class SkippableArrayList<E> extends ArrayList<E> {
        @NotNull
        public Iterator<E> iterator(Predicate<E> p, Either<E> f) {
            Iterator<E> it = super.iterator();
            return new Iterator<E>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public E next() {
                    E e = it.next();
                    if (p.apply(e))
                        return f.onSuccess(e);
                    else {
                        try {
                            f.onFailure(e);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return e;
                    }
                }
            };
        }
    }

    // 3.d
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        SkippableArrayList<Integer> skl = new SkippableArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 23; ++i)
            rand.nextInt(11);
        // invocazione con lambda come primo argomento
        skl.iterator((x) -> x > 5, new Either<>() {
            @Override
            public Integer onSuccess(Integer x) {
                return x + 1;
            }

            @Override
            public void onFailure(Integer x) throws Exception {
                l.add(x);
            }
        });

        // SINTASSI ALTERNATIVA: invocazione con anonymous class come primo argomento
        skl.iterator(new Predicate<>() {     // IntelliJ suggerisce di convertire questa anonymous class in una lambda: in tal caso riotterremo la sintassi di cui sopra
            @Override
            public boolean apply(Integer x) {
                return x > 5;
            }
        }, new Either<>() {
            @Override
            public Integer onSuccess(Integer x) {
                return x + 1;
            }

            @Override
            public void onFailure(Integer x) throws Exception {
                l.add(x);
            }
        });


    }

}

package it.unive.dais.po.appello.secondo_20_6_2018;

import java.util.ArrayList;
import java.util.Iterator;

public class FancyArrayList<E> extends ArrayList<E> {

    // es. 1.a
    public interface Function<A, B> {
        B apply(A a);
    }

    // es. 1.b
    public Iterator<E> iterator(int step, Function<E, E> f) {
        return new Iterator<>() {
            private int pos = step > 0 ? 0 : size() - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0 && pos < size();
            }

            @Override
            public E next() {
                E r = get(pos);
                pos += step;
                return f.apply(r);
            }
        };
    }

    // es. 1.c.i
    @Override
    public Iterator<E> iterator() {
        return iterator(1, (x) -> x);   // si può fare l'identità con la lambda (solo Java 8+)
    }

    // es 1.c.ii
    public Iterator<E> backwardIterator() {
        return iterator(-1, new Function<>() {  // oppure con una anonymous class semplicissima
            @Override
            public E apply(E x) {
                return x;
            }
        });
    }

    // es. 1.d
    public Iterator<E> iterator__refactored(int step, Function<E, E> f) {  // lo devo chiamare con un nome diverso da iterator() altrimenti questo sorgente non compila
        return new FancyIterator<>(this, step, f);
    }

    // questa classe è quasi uguale alla anoynmous class dell'es. 1.b, con la differenza che conserva la enclosing instance esplicitamente
    private static class FancyIterator<E> implements Iterator<E> {
        private FancyArrayList<E> enclosing;
        private Function<E, E> f;
        private int step, pos;

        public FancyIterator(FancyArrayList<E> parent, int step, Function<E, E> f) {    // il costruttore è l'unica differenza
            this.enclosing = parent;
            this.step = step;
            this.f = f;
            pos = step > 0 ? 0 : parent.size() - 1;
        }

        @Override
        public boolean hasNext() {  // questo metodo è uguale
            return pos >= 0 && pos < enclosing.size();
        }

        @Override
        public E next() {   // ed anche questo
            E r = enclosing.get(pos);
            pos += step;
            return f.apply(r);
        }
    }


}




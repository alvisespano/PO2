package it.unive.dais.po2.aa2020_21.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class IteratorTest {

    public static <A> Iterator<A> skipIterator(Iterator<A> it, int step) {
        // TODO per casa
    }

    // modo 1.A: rovesciare l'iteratore di input
    public static <A> Iterator<A> revIterator(Iterator<A> it) {
        // lista dritta, iteratore rovescio
        List<A> l = new ArrayList<>();
        while (it.hasNext()) {
            l.add(it.next());
        }
        return new Iterator<A>() {
            private int pos = l.size() - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public A next() {
                return l.get(pos--);
            }
        };
    }

    // modo 1.B: rovesciare l'iteratore di input
    public static <A> Iterator<A> revIterator2(Iterator<A> it) {
        // lista rovescia, iteratore dritto
        List<A> l = new ArrayList<>();
        while (it.hasNext()) {
            l.add(0, it.next());
        }
        return l.iterator();
    }

    // modo 2: rovesciare l'iteratore overridandolo
    public static class RevArrayList<T> extends ArrayList<T> {
        @Override
        public Iterator<T> iterator() {     // produce un iteratore che va al contrario
            return new Iterator<T>() {
                private int pos = RevArrayList.this.size() - 1;

                @Override
                public boolean hasNext() {
                    return pos >= 0;
                }

                @Override
                public T next() {
                    return RevArrayList.this.get(pos--);
                }
            };
        }
    }

    // classe che offre iteratori capaci di skippare ("saltare") elementi secondo un valore di step
    public static class SkipArrayList<T> extends ArrayList<T> {
        private final int step;

        public SkipArrayList(int step) {
            this.step = step;
        }

        @Override
        public Iterator<T> iterator() {     // produce un iteratore che va al contrario
            return new Iterator<T>() {
                private int pos = step < 0 ? SkipArrayList.this.size() - 1 : 0;

                @Override
                public boolean hasNext() {
                    return step < 0 ? (pos >= 0) : (pos < SkipArrayList.this.size());
                }

                @Override
                public T next() {
                    T x = SkipArrayList.this.get(pos);
                    pos += step;
                    return x;
                }
            };
        }
    }


    public static <A, B> Iterator<B> mapIterator(Iterator<A> it, Function<A, B> f) {

        return new Iterator<B>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public B next() {
                return f.apply(it.next());
            }
        };
    }

    public static <A, B> Iterator<B> mapAndRevIterator(Iterator<A> it, Function<A, B> f) {
        return revIterator(mapIterator(it, f));
    }


    public static void main(String[] args) {

        List<String> l = new SkipArrayList<>(-3);
        for (int i = 0; i < 10; ++i) {
            char[] a = new char[i];
            for (int j = 0; j < a.length; ++j)
                a[j] = 'a';
            l.add(new String(a));
        }

        Iterator<Integer> it = mapIterator(l.iterator(), (String s) -> s.length());
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }

    }


}

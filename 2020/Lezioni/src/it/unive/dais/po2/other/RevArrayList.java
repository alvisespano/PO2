package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RevArrayList<T> extends ArrayList<T> {
    public RevArrayList() {
        super();
    }

    public RevArrayList(int cap) {
        super(cap);
    }

    public static class RevIterator__static<T> implements Iterator<T> {
        private List<T> l;
        private int pos;

        public RevIterator__static(List<T> l) {
            this.l = l;
            this.pos = l.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public T next() {
            return l.get(pos--);
        }
    }

    private class RevIterator__nonstatic implements Iterator<T> {

        private int pos = RevArrayList.this.size() - 1;

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public T next() {
            return RevArrayList.this.get(pos--);
        }
    }

    @Override
    public Iterator<T> iterator() {
        //return new RevIterator<T>(this);            // classe globale
        //return new RevIterator__static<T>(this);    // classe nested STATICA
        //return new RevIterator__nonstatic();          // classe nested NON STATIC

        return new Iterator<T>() {                    // anonymous class
            private int pos = size() - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public T next() {
                return get(pos--);
            }
        };
    }
}

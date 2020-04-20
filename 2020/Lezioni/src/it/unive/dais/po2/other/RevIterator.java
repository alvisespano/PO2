package it.unive.dais.po2.other;

import java.util.Iterator;
import java.util.List;

public class RevIterator<T> implements Iterator<T> {
    private List<T> l;
    private int pos;

    /**
     * Costruttore
     * @param l List per poter chiamare size e get
     */
    public RevIterator(List<T> l) {
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


package it.unive.dais.po;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Collection<Integer> a = new ArrayList<>();
        Collection<Boolean> b = new ArrayList<>();

    }

    public static <A extends Comparable<B>, B> int compareMany(Collection<A> a, Collection<B> b) {
        Iterator<A> ia = a.iterator();
        Iterator<B> ib = b.iterator();
        int r = 0;
        while (ia.hasNext() && ib.hasNext()) {
            A x = ia.next();
            B y = ib.next();
            if (x.compareTo(y) != 0) r = -1;
        }
        return r;
    }


    public static <T> int compareMany(Collection<? extends Comparable<T>> a, Collection<? extends Comparable<T>> b) {
        Iterator<A> ia = a.iterator();
        Iterator<B> ib = b.iterator();
        int r = 0;
        while (ia.hasNext() && ib.hasNext()) {
            A x = ia.next();
            B y = ib.next();
            if (x.compareTo(y) != 0) r = -1;
        }
        return r;
    }


}

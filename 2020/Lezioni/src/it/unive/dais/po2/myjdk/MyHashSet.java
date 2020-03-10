package it.unive.dais.po2.myjdk;

public class MyHashSet<T> extends MyLinkedSet<T> {
    @Override
    public void add(T x) {
        MyIterator<T> it = iterator();
        boolean found = false;
        while (it.hasNext()) {
            T e = it.next();
            if (x.hashCode() == e.hashCode())
                found = true;
        }
        if (!found)
            l.add(x);
    }
}

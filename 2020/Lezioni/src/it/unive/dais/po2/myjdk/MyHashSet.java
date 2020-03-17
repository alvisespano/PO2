package it.unive.dais.po2.myjdk;

/**
 * Classe che fa veramente i set
 * non li fa l'interfaccia.
 * @param <T>
 */
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

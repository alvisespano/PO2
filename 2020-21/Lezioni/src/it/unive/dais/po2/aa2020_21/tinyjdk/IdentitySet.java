package it.unive.dais.po2.aa2020_21.tinyjdk;

public class IdentitySet<T> extends AbstractCollection<T> implements Set<T> {

    public IdentitySet() {
    }


    @Override
    public void add(T x) {
        if (!contains(x)) super.add(x);   // TODO risolvere il problema di ricorsione
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

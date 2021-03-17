package it.unive.dais.po2.aa2020_21.tinyjdk;

public class IdentitySet<T> extends AbstractResizableCollection<T> implements Set<T> {

    @Override
    public void add(T x) {
        if (!contains(x)) actualAdd(x);
    }

    @Override
    public Iterator<T> iterator() {
        // TODO rifattorizzare gli iteratori di ArrayList e spostarli in AbstractResizableCollection
        throw new RuntimeException();
    }
}

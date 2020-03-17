package it.unive.dais.po2.myjdk;

public abstract class MyAbstractCollection<T> implements MyCollection<T> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}

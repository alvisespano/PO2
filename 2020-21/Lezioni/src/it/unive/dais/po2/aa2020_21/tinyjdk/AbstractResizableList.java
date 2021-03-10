package it.unive.dais.po2.aa2020_21.tinyjdk;

public abstract class AbstractResizableList<T> extends AbstractResizableCollection<T> implements List<T> {
    @Override
    public T get(int index) {
        checkPos(index);
        return (T) a[index];
    }

    @Override
    public void set(int index, T x) {
        checkPos(index);
        a[index] = x;
    }

    @Override
    public void remove(int index) {
        // TODO rifattorizzare in maniera elegante e senza ripetizioni la remove(int), la remove(T) e la removeAll(T)
        for (int j = index; j < actualSize - 1; ++j)
            a[j] = a[j + 1];
        --actualSize;
    }

    @Override
    public void add(int index, T x) {
        // TODO da fare per casa
    }


    protected void checkPos(int index) {
        if (index < 0 || index >= size()) throw new RuntimeException(String.format("index %d is greater than size %d", index, size()));
    }

}

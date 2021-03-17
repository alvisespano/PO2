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
        if (actualSize - 1 - index >= 0) System.arraycopy(a, index + 1, a, index, actualSize - 1 - index);
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

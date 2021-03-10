package it.unive.dais.po2.aa2020_21.tinyjdk;

public abstract class AbstractResizableCollection<T> implements Collection<T> {

    protected Object[] a;
    protected int actualSize;

    // TODO creare una sottoclasse astratta con questa add() basata su array che si ridimensionano e lasciare la add() astratta in questa classe
    protected void actualAdd(T x) {
        if (actualSize >= a.length) {
            Object[] newa = new Object[a.length * 2];
            for (int i = 0; i < a.length; ++i)
                newa[i] = a[i];
            a = newa;
        }
        a[actualSize++] = x;
    }


    @Override
    public boolean contains(T x) {
        for (Object o_ : a) {
            T o = (T) o_;
            if (o.equals(x)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public void remove(T x) {
        assert x != null;
        for (int i = 0; i < actualSize; ++i) {
            T o = (T) a[i];
            if (o.equals(x)) {
                for (int j = i + 1; j < actualSize; ++j)
                    a[j - 1] = a[j];
                --actualSize;
                break;
            }
        }
    }

    @Override
    public void removeAll(T x) {
        // TODO da fare per casa
    }


}

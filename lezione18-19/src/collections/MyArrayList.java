package collections;

public class MyArrayList<E> implements MyCollection<E> {

    private Object[] a;
    private int actualSize = 0;


    public MyArrayList() {
        a = new Object[100];
    }

    @Override
    public void add(E o) {
        a[actualSize++] = o;
        if (actualSize >= a.length) {
            Object[] u = new Object[a.length * 2];
            for (int j = 0; j < a.length; ++j)
                u[j] = a[j];
            a = u;
        }
    }

    @Override
    public void remove(E o) {

    }

    @Override
    public MyIterator<E> getIterator() {
        return new MyIterator<E>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos <= actualSize;
            }

            @Override
            public E next() {
                return (E) MyArrayList.this.a[pos++];
            }
        };
    }


    public MyIterator<E> getBackwardsIterator() {
        return new MyIterator<E>() {
            private int pos = actualSize;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public E next() {
                return (E) MyArrayList.this.a[pos--];
            }
        };
    }

}

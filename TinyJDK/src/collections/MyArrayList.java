package collections;

import java.util.function.Function;

public class MyArrayList<T> implements MyList<T> {

    private Object[] a;
    private int actualSize;

    public static class MyException extends Exception {
        public MyException(String s) {
            super(s);
        }
    }

/*    T[] toArray() {
        return (T[]) a;
    }*/

/*    public static Exception returnNow() {
        return new Exception("msg");
    }

    public static void throwNow() throws Exception {
        throw new Exception("msg");
    }

    public static void caller() throws Exception {
        Exception e = returnNow();
        throwNow();
    }

    public static void caller2() {
        try {
            caller();
        }
        catch (Exception e2) {
            // fai qualcosa con e2
        }

    }

    public void m(int x) throws Exception {
        MyException e = new MyException("error messagge");
        if (x < 0) throw e;
    }
  */

    public MyArrayList() {
        clear();
    }

    @Override
    public int find(T x) throws NotFoundException {
        int cnt = 0;
        MyIterator<T> it = iterator();
        while (it.hasNext())
        {
            T y = it.next();
            if (x.equals(y)) return cnt;
            ++cnt;
        }
        throw new NotFoundException();
    }




    public static void main3() {
        MyArrayList<Integer> c = new MyArrayList<>();
        try {
            int index = c.find(6);
            System.out.println("found at index = " + index);
        } catch (NotFoundException e) {
            try {
                int index = c.find(7);
            } catch (NotFoundException e1) {

            }

        }
    }

    @Override
    public boolean contains(T x) {
        for (int i = 0; i < actualSize; ++i) {
            Object o = a[i];
            if (o.equals(x)) return true;
        }
        return false;
    }


    @Override
    public boolean contains(Function<T, Boolean> p) {
        return false;
    }

    @Override
    public int size() {
        return actualSize;
    }


    @Override
    public void clear() {
        a = new Object[100];
        actualSize = 0;
    }

    @Override
    public void add(T o) {
        a[actualSize++] = o;
        if (actualSize >= a.length) {
            Object[] u = new Object[a.length * 2];
            for (int j = 0; j < a.length; ++j)
                u[j] = a[j];
            a = u;
        }
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos <= actualSize;
            }

            @Override
            public T next() {
                return (T) MyArrayList.this.a[pos++];
            }
        };
    }

    @Override
    public void add(int i, T x) {

    }

    @Override
    public T get(int i) {
        return (T) a[i];
    }

    @Override
    public void set(int i, T x) {
        a[i] = x;
    }

    @Override
    public void remove(T x) {

    }

}

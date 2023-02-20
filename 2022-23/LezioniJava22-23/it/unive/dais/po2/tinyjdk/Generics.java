package it.unive.dais.po2.tinyjdk;

public class Generics {

    // JDK 1.5 (2004)

    public interface Iterator<T> {
        boolean hasNext();
        T next();
    }

    public interface Iterable<T> {
        Iterator<T> iterator();
    }

    public interface Collection<T> extends Iterable<T> {
        int size();
        void add(T x);
        void remove(T x);
        boolean contains(T x);
    }

    public interface List<T> extends Collection<T> {
        T get(int index);

        void set(int index, T x);
    }

    public static class ArrayList<T> implements List<T> {

        private T[] a;
        private int sz;

        public ArrayList(int capacity) {
            a = new T[capacity];
            sz = 0;
        }

        public ArrayList() {
            this(10);
        }

        @Override
        public int size() {
            return sz;
        }

        @Override
        public void add(T x) {
            if (sz >= a.length) {
                T[] old = a;
                a = new T[old.length * 2];
                for (int i = 0; i < old.length; ++i)
                    a[i] = old[i];
            }
            a[sz++] = x;
        }

        @Override
        public void remove(T x) {

        }

        @Override
        public boolean contains(T x) {
            return false;
        }

        @Override
        public Iterator<T> iterator() {
            return null;
        }


        @Override
        public T get(int index) {
            return a[index];
        }

        @Override
        public void set(int index, T x) {
            a[index] = x;
        }
    }


    public static void main(String[] args) {
        List<Zoo.Dog> c = new ArrayList<Zoo.Dog>();
        //c.add("ciao");
        //c.add(8);
        c.add(new Zoo.Dog(20, "Red"));

        for (int i = 0; i < c.size(); ++i) {
            Zoo.Animal a = c.get(i);

        }

    }

}


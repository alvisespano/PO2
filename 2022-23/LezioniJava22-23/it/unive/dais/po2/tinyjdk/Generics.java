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

        private Object[] a;
        private int sz;

        public ArrayList(int capacity) {
            a = new Object[capacity];
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
                Object[] old = a;
                a = new Object[old.length * 2];
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

        private class MyIterator implements Iterator<T> {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size();
            }

            @Override
            public T next() {
                return get(pos++);
            }
        }

        private static class MyStaticIterator<E> implements Iterator<E> {
            private int pos = 0;
            private ArrayList<E> l;

            public MyStaticIterator(ArrayList<E> l) {
                this.l = l;
            }

            @Override
            public boolean hasNext() {
                return pos < l.size();
            }

            @Override
            public E next() {
                return l.get(pos++);
            }
        }

        @Override
        public Iterator<T> iterator() {
            //return new MyIterator();
            return new MyStaticIterator<>(this);
            // TODO usare una anonymous class
        }

//        public static void main() {
//            List<Integer> l = new ArrayList<Integer>();
//            Iterator<Integer> it = l.iterator();
//            while (it.hasNext()) {
//                Integer n = it.next();
//                System.out.println(n);
//            }
//        }





        @Override
        public T get(int index) {
            return (T) a[index];
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


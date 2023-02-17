public class Generics {

    // JDK 1.0 (1994)

    public interface Iterator {
        boolean hasNext();
        Object next();
    }

    public interface Iterable {
        Iterator iterator();
    }

    public interface Collection extends Iterable {
        int size();
        void add(Object x);
        void remove(Object x);
        boolean contains(Object x);
    }

    public interface List extends Collection {
        Object get(int index);
        void set(int index, Object x);
    }

    public static class ArrayList implements List {

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
        public void add(Object x) {
            if (sz >= a.length) {
                Object[] old = a;
                a = new Object[old.length * 2];
                for (int i = 0; i < old.length; ++i)
                    a[i] = old[i];
            }
            a[sz++] = x;
        }

        @Override
        public void remove(Object x) {

        }

        @Override
        public boolean contains(Object x) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }


        @Override
        public Object get(int index) {
            return a[index];
        }

        @Override
        public void set(int index, Object x) {
            a[index] = x;
        }
    }


    public static void main(String[] args) {


    }

}

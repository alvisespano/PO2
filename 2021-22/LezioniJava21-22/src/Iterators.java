import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Iterators {

    public static class BackwardsArrayList<T> extends ArrayList<T> {

        public Iterator<T> originalIterator() {
            return super.iterator();
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int index = BackwardsArrayList.this.size() - 1;
                @Override
                public boolean hasNext() {
                    return index >= 0;
                }

                @Override
                public T next() {
                    return BackwardsArrayList.this.get(index--);
                }
            };
        }

        public static <X> Iterator<X> reverseIterator(Iterator<X> it) {
            List<X> buff = new ArrayList<>();
            while (it.hasNext()) buff.add(0, it.next());
            return buff.iterator();
        }

    }

    public static void main(String[] args) {
        Collection<Integer> l = new BackwardsArrayList<>();
        for (int i = 0; i < 20; l.add(i++));

        Iterator<Integer> it = l.iterator();
        while (it.hasNext())
            System.out.println(it.next());

        /*Iterator<Integer> it2 = l.originalIterator();
        while (it2.hasNext())
            System.out.println(it2.next());
        */
    }
}

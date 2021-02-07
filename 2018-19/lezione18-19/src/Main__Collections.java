import collections.Pair;
import zoo.Cane;
import zoo.PastoreTedesco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// esperimenti sulle collections
public class Main__Collections {


    public static class BackwardsArrayList<T> extends ArrayList<T> {

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int pos = BackwardsArrayList.this.size() - 1;
                @Override
                public boolean hasNext() {
                    return pos >= 0;
                }

                @Override
                public T next() {
                    return BackwardsArrayList.this.get(pos--);
                }
            };
        }
    }


    public static class PairwiseArrayList<E> extends ArrayList<E> {
        public Iterator<Pair<E, E>> pairiterator() {
            Iterator<E> it = super.iterator();
            return new Iterator<Pair<E, E>>() {
                private E a;
                @Override
                public boolean hasNext() {
                    a = it.next();
                    return it.hasNext();
                }

                @Override
                public Pair<E, E> next() {
//                    E a = it.next();
                    E b = it.next();
                    return new Pair<>(a, b);
                }
            };
        }
    }

    public static void main__iterator() {
        Collection<Cane> cani = new BackwardsArrayList<>();
        cani.add(new Cane());
        cani.add(new PastoreTedesco());

        Iterator<Cane> it = cani.iterator();
        while (it.hasNext()) {
            Cane c = it.next();
            c.abbaia();
        }

    }




}

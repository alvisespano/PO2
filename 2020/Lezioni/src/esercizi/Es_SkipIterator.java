package esercizi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Es_SkipIterator {

    public static class SkipArrayList<T> extends ArrayList<T> {
        @Override
        public Iterator<T> iterator() {
            // senza usare l'iteratore originale
            return new Iterator<T>() {
                private int pos = 0;
                @Override
                public boolean hasNext() {
                    return pos < size();
                }

                @Override
                public T next() {
                    T x = get(pos);
                    pos += 2;
                    return x;
                }
            };
            // usando l'iteratore originale
            /*Iterator<T> it = super.iterator();
            return new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public T next() {
                    T x = it.next();
                    if (it.hasNext()) it.next();
                    return x;
                }
            };*/
        }
    }


    public static void main(String[] args) {
        List<Integer> l = new SkipArrayList<>();
        for (int i = 0; i < 21; ++i)
            l.add(i);

        Iterator<Integer> it = l.iterator();
        while(it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }
    }



}

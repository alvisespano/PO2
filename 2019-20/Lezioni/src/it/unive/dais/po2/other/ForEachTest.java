package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ForEachTest {

    public static class StrangeArrayList<T> extends ArrayList<T> {
        @Override
        public void forEach(Consumer<? super T> c) {
            System.out.println("ciao");
            Iterator<T> it = iterator();
            int cnt = 0;
            while(it.hasNext() && cnt < 5) {
                T x = it.next();
                c.accept(x);
                ++cnt;
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> l1 = new StrangeArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l1.add(i);
        }

        for (Integer n : l1) {
            System.out.println(n);
        }
        l1.forEach(n -> System.out.println(n));


    }
}

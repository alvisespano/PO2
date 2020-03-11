package it.unive.dais.po2.other;

import java.util.*;

public class IteratorTest {

    public static void populate(Collection<Integer> a) {
        a.add(7);
        a.add(2);
        a.add(2);
        a.add(123);
    }

    public static void sum(Iterable<Integer> a) {
        int r = 0;
        for(Integer n : a) {
            r += n;
        }
        System.out.println(r);
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        populate(a);
        sum(a);

        Set<Integer> s = new HashSet<>();
        populate(s);
        sum(s);


        MyIntegerArrayList u = new MyIntegerArrayList();
        u.populate();
        u.sum();

    }

    public interface Summable extends Iterable<Integer> {

        default void sum() {
            int r = 0;
            for(Integer n : this) {
                r += n;
            }
            System.out.println(r);
        }

        void populate();
    }

    public static class MyIntegerArrayList extends ArrayList<Integer> implements Summable {

        public MyIntegerArrayList() {
            super();
        }

        @Override
        public void populate() {
            add(7);
            add(2);
            add(2);
            add(123);
        }
    }

}

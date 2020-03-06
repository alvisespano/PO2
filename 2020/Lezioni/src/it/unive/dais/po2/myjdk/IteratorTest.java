package it.unive.dais.po2.myjdk;

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
        Iterator<Integer> it = a.iterator();
        while(it.hasNext()) {
            Integer n = it.next();
            r += n;
        }
        System.out.println(r);
    }

    public static void sum2(Iterable<Integer> a) {
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


    }
}

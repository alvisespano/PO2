package it.unive.dais.po2.aa2020_21.generics;

import java.util.*;

public class TestCollections {
    private static Random rnd = new Random();

    public static void populateCollection(Collection<Integer> c) {
        for (int i = 0; i < rnd.nextInt(100); ++i)
            c.add(i);
    }


    public static void printCollection(Iterable<Integer> c) {
        Iterator<Integer> it = c.iterator();
        while(it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }
    }

    public static void main(String[] args) {

        Collection<Integer> c1 = new LinkedList<>();
        populateCollection(c1);
        printCollection(c1);

        Collection<Integer> c2 = new ArrayList<>();
        populateCollection(c2);
        printCollection(c2);

        Collection<Integer> c3 = new HashSet<>();
        populateCollection(c3);
        printCollection(c3);

    }


}

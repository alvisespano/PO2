package it.unive.dais.po2.aa2020_21.generics;

import java.util.*;

public class TestCollection {


    private static Random rnd = new Random();

    public static void populate(Collection<Integer> c) {
        for (int i = 0; i < rnd.nextInt(); ++i)
            c.add(i);
    }

    public static void print(Iterable<Integer> c) {
        Iterator<Integer> it = c.iterator();
        while(it.hasNext()) {
            Integer n = it.next();
            System.out.println(n);
        }

        /* FOR EACH è uno ZUCCHERO SINTATTICO
        for (T x : e) {
            st1;
            ...
            stn;
        }

        ----->>>> si DEZUCCHERA in questo:

        Iterator<T> it = e.iterator();
        while(it.hasNext()) {
            T x = it.next();
            st1;
            ...
            stn;

        }
        */
    }

    public static double average(Collection<Integer> c) {
        double r = 0.;
        for (int n : c) {
            r += n;
        }
        return r / c.size();
    }

    public static int median(List<Integer> c) {
          Collections.sort(c);  // ordiniamo la List<Integer>
          return c.get(c.size() / 2);
    }

    public static int median(Collection<Integer> c) {
        Iterator<Integer> it = c.iterator();
        int r = 0;
        for (int i = 0; i < c.size() / 2; ++i)
            r = it.next();
        return r;
    }


    public static void main(String[] args) {
        Collection<Integer> a = new ArrayList<>();
        populate(a);
        print(a);
        System.out.println(String.format("avg = %g, median = %d", average(a), median(a)));

        LinkedList<Integer> b = new LinkedList<>();
        populate(b);
        print(b);
        System.out.println(String.format("avg = %g, median = %d", average(b), median(b)));

        HashSet<Integer> c = new HashSet<>();
        populate(c);
        print(c);
        System.out.println(String.format("avg = %g, median = %d", average(c), median(c)));  // c non è una List<Integer> quindi chiama l'overload median(Collection<Integer>)



    }



}

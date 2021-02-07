package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class RevTest {

    public static void main(String[] args) {
        Collection<Integer> l = new RevArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }

        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }
    }
}

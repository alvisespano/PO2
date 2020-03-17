package it.unive.dais.po2.other;

import java.util.Iterator;

public class RevTest {
    public static void main(String[] args) {

        RevArrayList<Integer> l = new RevArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }
    }
}

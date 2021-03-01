package it.unive.dais.po2.aa2020_21.tinyjdk;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        it.unive.dais.po2.aa2020_21.tinyjdk.Collection<Integer> c = new java.util.ArrayList<Integer>();

        for (Integer n : c) {
            System.out.println(n);
        }



        ArrayList<String> u = new ArrayList<>();
        u.add("ciao");
        u.add("mi");
        u.add("chiamo");
        u.add("Alvise");

        boolean b = u.contains("chiamo");

    }

}

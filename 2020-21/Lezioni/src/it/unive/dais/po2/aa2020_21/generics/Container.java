package it.unive.dais.po2.aa2020_21.generics;

import java.util.ArrayList;

public class Container {

    public static class MyIntList {

        private final int[] a;
        private int cnt;

        public MyIntList() {
            a = new int[100];
            cnt = 0;
        }

        public void add(int v) {
            a[cnt++] = v;
        }

        public int size() {
            return cnt;
        }

        public int get(int i) {
            return a[i];
        }
    }

    public static class MyPolyList {

        private final Object[] a;
        private int cnt;

        public MyPolyList() {
            a = new Object[100];
            cnt = 0;
        }

        public void add(Object v) {
            a[cnt++] = v;
        }

        public int size() {
            return cnt;
        }

        public Object get(int i) {
            return a[i];
        }
    }

    public static class MyGenList<T> {

        private final Object[] a;
        private int cnt;

        public MyGenList() {
            a = new Object[100];
            cnt = 0;
        }

        public void add(T v) {
            a[cnt++] = v;
        }

        public int size() {
            return cnt;
        }

        public T get(int i) {
            return (T) a[i];
        }
    }



    public static void main(String[] args) {

        // monomorfo per int
        {
            // costruzione
            MyIntList l = new MyIntList();

            // popolamento
            for (int i = 0; i < 10; ++i)
                l.add(i);

            // lettura
            for (int i = 0; i < l.size(); ++i)
                System.out.println(l.get(i));
        }

        // polimorfo subtyping
        {
            // costruzione
            MyPolyList l = new MyPolyList();

            // popolamento
            for (int i = 0; i < 10; ++i)
                l.add(i);

            l.add("ciao");
            l.add(6.78);
            l.add(new ArrayList<Integer>());

            // lettura
            for (int i = 0; i < l.size(); ++i) {
                String x = (String) l.get(i);
                String y = x.toLowerCase();
                System.out.println(y);
            }
        }

        // generics
        {
            // costruzione
            MyGenList<Integer> l = new MyGenList<Integer>();

            // popolamento
            for (int i = 0; i < 10; ++i)
                l.add(i);

            // lettura
            for (int i = 0; i < l.size(); ++i) {
                Integer n = l.get(i);
                System.out.println(n);
            }
        }

        // generics altro esempio
        {
            // costruzione
            MyGenList<Zoo.Animal> l = new MyGenList<Zoo.Animal>();

            // popolamento
            l.add(new Zoo.Dog());
            l.add(new Zoo.Cat());
            l.add(new Zoo.Cocker());

            // lettura
            for (int i = 0; i < l.size(); ++i) {
                Zoo.Animal n = l.get(i);    // getto sempre oggetti di tipo Zoo.Animal perché l : MyGenList<Zoo.Animal>
                System.out.println(n);
            }
        }
    }


}

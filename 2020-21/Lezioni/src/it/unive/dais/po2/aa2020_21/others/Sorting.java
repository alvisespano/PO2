package it.unive.dais.po2.aa2020_21.others;

import it.unive.dais.po2.aa2020_21.generics.Zoo;
import java.util.*;
import java.util.function.Function;
import static it.unive.dais.po2.aa2020_21.functional.IteratorTest.mapIterator;

public class Sorting {


    public static void main(String[] args) {

        Collection<Zoo.Animal> c = new ArrayList<>();

        Zoo.Dog fido = new Zoo.Dog();
        Zoo.Dog pluto = new Zoo.Dog();
        c.add(fido);

        // static <T> boolean addAll(Collection<? super T> c, T... elements)
        //Collections.addAll(c, fido, pluto);   // TODO da rivedere dopo

        List<Cane> c2 = new ArrayList<>();
        sort(c2);

        sort(c2, new Comparator<Cane>() {   // (cane1, cane2) -> { return cane1.peli - cane2.peli; }
            @Override
            public int compare(Cane cane1, Cane cane2) {
                return cane1.peli - cane2.peli;
            }
        });

        sort(c2, new Comparator<Animale>() {
            @Override
            public int compare(Animale a, Animale b) {
                return a.peso - b.peso;
            }
        });


        List<Girasole> c3 = new ArrayList<>();
        sort(c3, (o1, o2) -> o1.foglie - o2.foglie);

        Iterator<Number> r = mapIterator(c2.iterator(), Sorting::mymapfun);

        // altri test coi wildcard

        List<? super Cane> u1 = new ArrayList<>();
        u1.add(new Cane(50, 50));
        u1.add(new Dalmata(50, 50, 50));
        Cane ca1 = u1.get(0);
        Animale ca2 = u1.get(0);

        List<? extends Cane> u2 = new ArrayList<>();
        u2.add(new Cane(50, 50));
        u2.add(new Dalmata(50, 50, 50));
        Cane ca3 = u2.get(0);
        Animale ca4 = u2.get(0);

    }

    private static Integer mymapfun(Animale a) {
        return a.peso;
    }

    public static class Creatura {}


    public static class Pianta extends Creatura {
        public int foglie;
    }

    public static class Girasole extends Pianta {}


    public static class Animale extends Creatura implements Comparable<Animale> {
        protected int peso;
        public Animale(int peso) {
            this.peso = peso;
        }
        public void eat(Animale a) {
            this.peso += a.peso;
        }

        public Animale mate(Animale a) {
            return new Animale((this.peso + a.peso) / 2);
        }

        @Override
        public int compareTo(Animale x) {
            return this.peso - x.peso;
        }

        public void m() {}
        public void m(int x) {}
        public double m(double y, int x) { return 0.; }
        public int m(Animale a) { return 1; }
        public void m(Cane a) {}
        //public int m(Cane a) {}     // overload invalido perché ambiguo
    }

    public static class Cane extends Animale {
        protected int peli;

        public Cane(int peso, int peli) {
            super(peso);
            this.peli = peli;
        }

        @Override
        public void eat(Animale a) {
            this.peso += this.peso / 2;
        }

        @Override
        public Cane mate(Animale a) {
            return new Cane((this.peso + a.peso) / 3, this.peli);
        }

        @Override
        public int compareTo(Animale x) {
            if (x instanceof Cane) {
                Cane y = (Cane) x;
                return (this.peso * this.peli) - (y.peso * y.peli);
            }
            return super.compareTo(x);
        }

    }

    public static class Dalmata extends Cane {
        public int chiazze;

        public Dalmata(int peso, int peli, int chiazze) {
            super(peso, peli);
            this.chiazze = chiazze;
        }
    }

    static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    static <T>                               void sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
    }





}

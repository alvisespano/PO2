package it.unive.dais.po2.aa2020_21.others;

import it.unive.dais.po2.aa2020_21.generics.Zoo;
import java.util.*;

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
        sort(c2, (cane1, cane2) -> cane1.peli - cane2.peli);

        List<Girasole> c3 = new ArrayList<>();
        sort(c3, (o1, o2) -> o1.foglie - o2.foglie);


    }

    public static class Pianta {
        public int foglie;
    }

    public static class Girasole extends Pianta {}


    public static class Animale implements Comparable<Animale> {
        protected int peso;
        public Animale(int peso) {
            this.peso = peso;
        }
        public void eat(Animale a) {
            this.peso += a.peso;
        }

        @Override
        public int compareTo(Animale x) {
            return this.peso - x.peso;
        }
    }

    public static class Cane extends Animale {
        protected int peli;

        public Cane(int peso, int peli) {
            super(peso);
            this.peli = peli;
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


    interface __Comparable<T> {
        int compareTo(T x);
    }

    static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    static <T>                       void sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
    }


}

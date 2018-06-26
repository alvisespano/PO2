package it.unive.dais.po.appello.secondo_20_6_2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorting {

    // test 4

    // funzione sort data dal testo
    static <T extends Comparable<T>> void sort(List<T> l) {
        Collections.sort(l);    // semplice stub alla sort del JDK, così fa qualcosa se provate a lanciarlo
    }

    // test 4.a
    static <T> void sort_(List<? extends Comparable<T>> l) { // se decommentate l'underscore nel nome diventa un overload non valido perché ha la stessa erasure della sort qui sopra
    }

    // classi date dal testo
    public static class Humanoid implements Comparable<Humanoid> {
        protected int strength;

        public Humanoid(int strength) {
            this.strength = strength;
        }

        @Override
        public int compareTo(Humanoid o) {
            return -(strength - o.strength);
        }
    }

    public static class Elf extends Humanoid {
        protected int mana;

        public Elf(int strength, int mana) {
            super(strength);
            this.mana = mana;
        }

        @Override
        public int compareTo(Humanoid o) {
            if (o instanceof Elf) {
                Elf e = (Elf) o;
                return -((mana + strength) - (e.mana + e.strength));
            }
            else return super.compareTo(o);
        }

    }

    // test 4.b
    public static void main_b(String[] args) {
        List<Elf> l = new ArrayList<>();
        l.add(new Elf(11, 23));
        // decommentate questa chiamata se volete compilare questo sorgente e lanciarlo
        //sort(l);    // non compila perché Elf non implementa Comparable<Elf>, ma solamente Comparable<Humanoid> (ereditato dalla superclasse)

    }

    // test 4.c
    public static void main(String[] args) {
        List<Humanoid> l = new ArrayList<>();
        Humanoid a = new Elf(10, 8), b = new Humanoid(8), c = new Humanoid(12);
        l.add(a); l.add(b); l.add(c);
        sort(l);    // usate il debugger per vedere gli elementi della lista ordinata
    }
}


/*
 RISPOSTE TEST TEMA A:
 (1): 4
 (2): 1
 (3): 4
 (4.a): 2
 (4.b): 1
 (4.c): 2

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// questo sorgente contiene il codice relativo alla domanda 4 del test a risposta multipla (tema A)

public class Test {

    // funzione sort data dal testo
    static <T extends Comparable<T>> void sort(List<T> l) {
        Collections.sort(l);    // stub alla sort del JDK
    }

    // test 4.a
    // se si rimuove l'underscore dal nome del seguente metodo sort_(), il compilatore segnalerà un overload non valido perché i due metodi sort() avranno la stessa type erasure
    static <T> void sort_(List<? extends Comparable<T>> l) {
    }

    // classi date dal testo
    public static class Humanoid implements Comparable<Humanoid> {
        protected int strength;

        public Humanoid(int strength) {
            this.strength = strength;
        }

        @Override
        public int compareTo(Humanoid o) {
            return -(strength - o.strength);     // il segno meno nega il risultato intero, quindi inverte la direzione dell'ordinamento
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
            if (o instanceof Elf) { // questa guardia non valuta mai true, perché nessun Elf viene mai confrontato con un altro Elf (vedi NOTA in basso)
                Elf e = (Elf) o;
                return -((mana + strength) - (e.mana + e.strength));
            } else return super.compareTo(o);
        }

    }

    // test 4.b
    public static void main_b(String[] args) {
        List<Elf> l = new ArrayList<>();
        l.add(new Elf(11, 23));
        sort(l);    // non compila perché Elf non implementa Comparable<Elf>, ma solamente Comparable<Humanoid> (ereditato dalla superclasse)

    }

    // test 4.c
    public static void main(String[] args) {
        List<Humanoid> l = new ArrayList<>();
        Humanoid a = new Elf(10, 8),  // si badi che solo un oggetto è di tipo Elf, gli altri sono di tipo Humanoid
                 b = new Humanoid(8),
                 c = new Humanoid(12);
        l.add(a);
        l.add(b);
        l.add(c);
        sort(l);
    }
}

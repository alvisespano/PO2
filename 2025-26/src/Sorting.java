import tinyjdk.dais.unive.it.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static class Piatto implements Comparable<Piatto> {
        private List<String> ingredienti;

        public Piatto(List<String> ingredienti) {
            this.ingredienti = ingredienti;
        }

        @Override
        public int compareTo(Piatto o) {
            return ingredienti.size() - o.ingredienti.size();
        }

        public int numero_ingredienti() { return ingredienti.size(); }
    }

    public static class PiattoVegano extends Piatto {
        private int costo;

        public PiattoVegano(List<String> ingredienti, int costo) {
            super(ingredienti);
            for (String s : ingredienti) {
                assert(isVegan(s));
            }
            this.costo = costo;
        }

        public static boolean isVegan(String s) { return true; }

        @Override
        public int compareTo(Piatto o) {
            if (o instanceof PiattoVegano)
                return costo - ((PiattoVegano) o).costo;
            else return -1;
        }

    }

    public static void main(String[] args) {
        {
            Piatto carbonara = new Piatto(List.of("uova", "guanciale", "pecorino romano", "pasta"));
            Piatto polloArrosto = new Piatto(List.of("pollo", "olio", "rosmarino", "patate"));
            Piatto frittura = new Piatto(List.of("calamari", "gamberetti", "totani", "schie", "seppioline"));
            List<Piatto> l = List.of(carbonara, polloArrosto, frittura);
            Collections.sort(l);
        }

        {
            List<Integer> l = List.of(45, 78, 234, -456, 2);

            Collections.sort(l, (a, b) -> a - b);
        }
        {
            Zoo.Animal fido = new Zoo.Animal(40);
            Zoo.Animal gigio = new Zoo.Animal(5);
            Zoo.Animal pippo = new Zoo.Animal(140);
            Zoo.Animal pluto = new Zoo.Animal(12);
            List<Zoo.Animal> l = List.of(fido, gigio, pippo, pluto);

            Collections.sort(l, (a1, a2) -> a2.weight - a1.weight);

        }
    }

}

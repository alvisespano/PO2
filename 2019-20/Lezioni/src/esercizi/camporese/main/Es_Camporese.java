package esercizi.camporese.main;

import esercizi.camporese.Persona;
import esercizi.camporese.Studente;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Es_Camporese {

    @NotNull
    public static <A, B> ArrayList<B> mapFilter(Iterable<A> l, boolean ancheBocciati, Function<? super A, ? extends B> f) {
        ArrayList<B> c = new ArrayList<>();
        for (A a : l)
            if (ancheBocciati || (((Studente) a).getMediaVoti()) >= 6)
                c.add(f.apply(a));
        return c;
    }

    public static <A, B> B fold(Iterable<A> l, B zero, BiFunction<A, B, B> f) {
        B acc = zero;
        for (A a : l)
            acc = f.apply(a, acc);
        return acc;
    }

    public static <A> void printIterable(Iterable<A> l, String str) {
        System.out.println(str);
        for (A a : l)
            System.out.println(a);
    }


    public static void main(String[] args) {
        ArrayList<Persona> persone = new ArrayList<>();
        persone.add(new Persona("Alvise", 43));
        persone.add(new Persona("Federico", 26));
        persone.add(new Persona("Stefano", 35));
        persone.add(new Persona("Matteo", 29));
        persone.add(new Studente("Francesco", 20, 2, 3));

        /*USA PERSONA::COMPARETO*/
        Collections.sort(persone);
        /*USA COMPARE*/
        /*
        Collections.sort(persone, (o1, o2) -> o1.età - o2.età);
        */
        printIterable(persone, "\nPERSONE ORDINATE PER ETA':\n");

        ArrayList<Studente> studenti = new ArrayList<>();
        studenti.add(new Studente("Francesco", 20, 2, 3));
        studenti.add(new Studente("Andrea", 23, 2, 6));
        studenti.add(new Studente("Daniele", 22, 3, 5));
        studenti.add(new Studente("Michele", 21, 2, 7));
        studenti.add(new Studente("Nicola", 20, 1, 10));

        /*
        NON PUOI USARE Collections.sort(studenti); SE NON USI I GENERICS SU PERSONA
        perchè useresti il compareTo di Persona e non quello di Studente
        siccome Studente estende Persona che implementa Comparable<Persona>
        */
        /*USA STUDENTE::COMPARETO*/
        //Collections.sort(studenti);
        /*USA COMPARE*/
        studenti.sort((o1, o2) -> {
            int diffAnnoCorso = o1.getClasse() - o2.getClasse();
            return diffAnnoCorso == 0 ? o1.eta - o2.eta : diffAnnoCorso;
        });
        printIterable(studenti, "\nSTUDENTI ORDINATI PER (ANNOCORSO, ETA'):\n");

        ArrayList<Studente> tuttiVengonoPromossi = mapFilter(studenti, true, (x) ->
        {
            Studente daRestituire = x.clone();
            daRestituire.prossimoAnno();
            return daRestituire;
        });
        printIterable(tuttiVengonoPromossi, "\nSTUDENTI CHE SI SPERA ARRIVINO NELLA NUOVA CLASSE:\n");

        ArrayList<Studente> studentiPromossi = mapFilter(studenti, false, (x) ->
        {
            Studente daRestituire = x.clone();
            daRestituire.prossimoAnno();
            return daRestituire;
        });
        printIterable(studentiPromossi, "\nSTUDENTI PROMOSSI VERAMENTE:\n");

        System.out.println("\nMEDIA VOTI DELLE PERSONE PROMOSSE: " + fold(tuttiVengonoPromossi, 0, (Studente x, Integer acc) -> acc + x.getMediaVoti()) / tuttiVengonoPromossi.size() + "\n");
    }
}


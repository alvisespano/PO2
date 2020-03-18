package it.unive.dais.po2.other;

import java.util.*;

public class IteratorTest {

    public static void populate(Collection<Integer> a) {
        a.add(7);
        a.add(2);
        a.add(2);
        a.add(123);
    }

    /*
     *      Iterator<Integer> it = a.iterator();
     *      while(it.hasNext()){
     *          Integer n = it.next();
     *          r += n;
     *   Questo ciclo è uguale al for smart
     *   che c'è sulla funzione sum qui sotto
     *
     */
    public static void sum(Iterable<Integer> a) {
        int r = 0;
        for(Integer n : a) {
            r += n;
        }
        System.out.println(r);
    }

    public static void main2(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        populate(a);
        sum(a);

        Set<Integer> s = new HashSet<>();
        populate(s);
        sum(s);

        // oggetto con costruttore vuoto
        MyIntegerArrayList u = new MyIntegerArrayList();
        // populate(u); populate statica
        u.populate(); // populate non statica
        u.sum(); // sum non statica

    }

    /**
     * Interfaccia che rappresenta i tipi sommabili (tipo specifico Integer).
     * Classe che sarebbe da mettere in un altro file.
     * Messa qui solo per non switchare continuamente file.
     */
    public interface Summable extends Iterable<Integer> {

        /*
         * Implementazione di un default:
         * implementazione di un metodo all'interno di un'interfaccia.
         * Devono essere implementazioni che non dipendono da alcun metodo o altro.
         */
        default void sum() {
            int r = 0;
            /*
            * Invece di fare il for each sul parametro 'a' che prima passavo
            * ora itera su me stesso 'this'
            */
            for(Integer n : this) {
                r += n;
            }
            System.out.println(r);
        }

        void populate();
    }


    public static class MyIntegerArrayList extends ArrayList<Integer> implements Summable {

        public MyIntegerArrayList() {
            super();
        }

        @Override
        public void populate() {
            add(7); // = this.add --> add ereditata da ArrayList
            add(2);
            add(2);
            add(123);
        }
    }






    public static void main(String[] args) {

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }


        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }


        for (int n : l) {
            System.out.println(n);
        }

    }


    /*
    * INSEGNAMENTO LEZIONE 1 del 07/03/2020
    * Si può programmare a metodi statici ai quali passi degli argomenti e loro fanno qualcosa su questi argomenti
    * Oppure si può programmare più puramente ad oggetti cioè con metodi non statici ma
    * per programmare con metodi non statici allora tutte le operazioni che fai su this,
    * non le fai sull'argomento, quindi i metodi perdono un parametro (sono senza parametro in questo caso)
    * perché lavorano sull'oggetto a sinistra del punto nell'invocazione del metodo.
    */

    /*
    * SUBSUNCTION
    * Quando c'è troppa genericità ci sono dei problemi, perché non posso fare le operazioni con qualsiasi tipo.
    * Un'interfaccia a volte è sbagliata che sia generica per un qualsiasi tipo T.
    * Generalizzare troppo ci porta a non implementare nulla, e lasciare implemenatre ai sottotipi.
    *
    * OVERLAP: intersezione, ovvero due cose che si stanno ripetendo.
    *
    * Quando si EREDITA, si eredita codice.
    * Quando si IMPLEMENTANO interfacce, sono sempre supertipo, non si eredita codice ma solo firme.
    *
    */

}

package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


public class FunctionalTest {

    /**
     * Map funzione che ritorna una collection di tutti i risultati
     * @param l
     * @param f
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> Collection<B> map(Collection<A> l, Function<A, B> f) {
        Collection<B> c = new ArrayList<>();
        for (A a : l) {
            B b = f.apply(a);
            c.add(b);
        }
        return c;
    }


    /*
    * La stampa non ha una computazione (void),
    * è un side effect, modifica solo lo stato della macchina
    * Non restituisce alcun risultato.
    */
    public static <X> void print(Collection<X> c) {
        /*
         * void non è un tipo e non può essere usato come generics
         * Void è un tipo
         *      -> è una classe inventata solamente per esprimere sottoforma di tipo che non ritorni nulla
         *          -> è per questo che alla fine c'è un return null;
         */
        map(c, new Function<X, Void>() {    //funzione che ritorna un tipo Collection<Void>
            @Override
            public Void apply(X x) {
                System.out.println(x);
                return null;
            }
        });
        /*
         * map(c, (Function<X, Void>) x -> {
         *       System.out.println(x);
         *       return null;
         * });
         *
         * Semplificata
         * map(c, x -> {System.out.println(x); return null;});
         *        parametro -> {blocco espressioni}
         */
    }


    private static class MiaFunzionePerMap implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer x) {
            return x + 1;
        }
    }


    public static void main(String[] args) {

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }
        print(l);

        /* il tipo dell'espressione da new a } ha tipo Function<Integer, Integer>
        * l'espressione ha lo stesso tipo della cosa che costruisco (dopo new)
        */
        Collection<Integer> r = map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });
        // questa è la stessa cosa fatta con una lambda
        Collection<Integer> r2 = map(l, x -> x + 1);
        // questa terza variante usa direttamente una classe NON-anonima
        Collection<Integer> r3 = map(l, new MiaFunzionePerMap());

        print(l);




    }
}

/*
*       TRADUZIONE DA ANONYMOUS CLASS A LAMBDA
*       LA LAMBDA E' UNO ZUCCHERO SINTATTICO PER UNA ANONYMOUS CLASS
*       Collection<B> r = map(l, new Function<A, B>() {
*           @Override
*           public B apply(A x) {
*               BODY;
*           }
*       });
*
*       Collection<B> r = map(l, x -> BODY);
*/

/*
* ESPRESSIONI:  right value
*               Esempio:
*                   x > 0 ? x+1 : x-1;
*
* STATEMENT:    quello che sta su una riga sola con il ';' alla fine
*               è un'istruzione o un blocco intero è uno statement
*               (for { blocco } / if (cond) { blocco })
*               Esempio:
*                    if (x > 0)
 *                       return x+1;
 *                   else
 *                       return x-1;
*
* Nelle Lambda se:
*   - uso un'espressione da sola non occorrono le graffe.
*   - se uso uno statement devo usare le graffe.
*
* LAMBDA: il tipo è dedotto da Java (tipe inference, in Java, c'è solo nelle lambda)
* Collection<Integer> r2 = map(l, x -> x + 1);
* LAMBDA CON ARGOMENTO TIPATO (lambda with type inference)
* Collection<Integer> r2 = map(l, (Integer x) -> x + 1);
*
* TYPE INFERENCE: tipi dedotti dal compilatore (solo nelle lambda, in Java)
* Il tipo delle lambda è il tipo che avrebbe l'anonymus class di cui la lambda è zucchero sintattico,
* ovvero il tipo di un'interfaccia
*
*
*
*
* MIN 36:41 lezione 7
 */

package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalTest {

    /* Funzione sostituita da import java.util.function.Function;
     * Function: prende qualcosa e ritorna qualcosa
     *  public interface Function<T,S> {
     *    S apply(T x);
     *  }
    */

    /* Funzione sostituita di import java.util.function.Supplier;
     * Supplier: non prende nulla ma ritorna qualcosa
     * public interface Supplier<T> {
     *     T get();
     * }
    */

    /* Funzione sostituita di import java.util.function.Supplier;
     * Consumer: prende qualcosa ma non ritorna nulla
     * public interface Consumer<T> {
     *     T accept();
     * }
     */

    /*
     * Runnable: non prende niente e non ritorna nulla
     * public interface Rubbable {
     *     T run();
     * }
     */

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


    /**
     * Classe MiaFunzionePerMap
     * Implementa Function<Integer, Integer>
     */
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


        // LE 4 FORME DI LAMBDA IN JAVA
        Function<Integer, Integer> f = x -> x + 1;

        Function<Integer, Integer> f2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        };

        // Lambda diverse da Function
        Supplier<Integer> g = () -> {
            if (l.size() > 4) return 1;
            else return 2;
        };

        Supplier<Integer> g2 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                if (l.size() > 4) return 1;
                else return 2;
            }
        };

        // Lambda che non computa nulla (non ritorna) ma fa qualcosa (stampa)
        Consumer<Integer> h = (x) -> {  // x è Integer
            for (int i = 0; i < x; ++i)
                System.out.println(i);
        };

        Consumer<Integer> h2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                for (int i = 0; i < x; ++i)
                    System.out.println(i);
            }
        };

        // Lambda che non prende nulla e non ritorna nulla
        Runnable runnable1 = () -> {
            System.out.println("ciao");
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("ciao");
            }
        };


        // ESEMPI DI CHIAMATA ALLA map()

        // questa è la stessa cosa fatta con una lambda
        Collection<Integer> x1 = map(l, x -> x + 1);

        /* il tipo dell'espressione da new a } ha tipo Function<Integer, Integer>
         * l'espressione ha lo stesso tipo della cosa che costruisco (dopo new)
         */
        Collection<Integer> x2 = map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });

        // questa terza variante usa direttamente una classe NON-anonima, ha tipo MiaFunzionePerMap
        Collection<Integer> x3 = map(l, new MiaFunzionePerMap());


        print(l);
    }
}

/*
* TRADUZIONE DA ANONYMOUS CLASS A LAMBDA
* LA LAMBDA E' UNO ZUCCHERO SINTATTICO PER UNA ANONYMOUS CLASS
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
*______________________________________________________________________________________________________________________
* LAMBDA EXPRESSION
*
* Nelle Lambda se:
*   - uso un'espressione da sola non occorrono le graffe.
*   - se uso uno statement devo usare le graffe.
*
* LAMBDA: il tipo è dedotto da Java (type inference, in Java, c'è solo nelle lambda)
* Collection<Integer> r2 = map(l, x -> x + 1);
* LAMBDA CON ARGOMENTO TIPATO (lambda with type inference)
* Collection<Integer> r2 = map(l, (Integer x) -> x + 1);
*
* ======================================================================================================================
* Una lambda, in Java, deve essere compatibile non solo con il tipo Function
* ma con qualunque tipo, l'importante è che abbia:
*  - un solo metodo;
*  - un solo argomento in tipo di ritorno.
*
* Dentro alle lambda non si possono usare variabili non final che appartengono allo scope.
*
* La lambda è uno zucchero sintattico delle anonymus class aventi UN solo metodo.
*
* Le lambda permettono di scrivere dei pezzi di codice con scoping condiviso,
* quindi posso accedere alle variabili che ho nello scope del chiamante
* anche da dentro alla lambda, è un modo molto più moderno di programmare.
*======================================================================================================================
*
* TYPE INFERENCE: tipi dedotti dal compilatore (solo nelle lambda, in Java)
* Il tipo delle lambda è il tipo che avrebbe l'anonymus class di cui la lambda è zucchero sintattico,
* ovvero il tipo di un'interfaccia
*
*______________________________________________________________________________________________________________________
*
* CLASSE ASTRATTA
* Una classe astratta è una classe che ha almeno un metodo astratto
* Il metodo astratto serve a dire che metto:
*  - nome
*  - #parametri
*  - tipo di ritorno
* ma non dico cosa fa, chi la eredita è obbligato a implementare quel metodo altrimenti
* non può istanziare la classe.
* Una classe astratta nel mondo dei thread sono un ottimo modo per realizzare un sistema di thread
* in un linguaggio ad oggetti perché rappresenta:
*  - un thread come una classe astratta, dove ci sono tutte le funzionalità:
*       - per farlo partire
*       - ucciderlo
*       - aspettarlo
*       - interromperlo
*    manca solo cosa deve fare, che deve essere definito da chi sta sotto.
* ______________________________________________________________________________________________________________________
*
* ANNOTAZIONI
* Le annotazioni servono a scrivere delle cose speciali per decorare i metodi/campi/classi
* con informazioni che il linguaggio non ha.
* Le annotazioni servono al compilatore, che quando la vede si comporta in maniera diversa.
* Si può dire che cosa devono fare.
* Esempio:
*   quando un programmatore si definisce una propria interfaccia funzionale
*       @FunciotnalInteface
*       public interface Funct<T,S> {
*           S exec(T x);
*       }
*   che dice al compilatore Java che quell'interfaccia è una di quelle per trasformare la lambra,
*   quindi il compilatore sa che:
*    - deve essere un interfaccia con un solo metodo;
*    - non glie ne interessa di come si chiama il metodo;
*    - può usarla per trasformare le lambda.
* Queste annotazioni sono decorazioni che, non sono key-word, fanno fare delle cose speciali al compilatore.
*
*/

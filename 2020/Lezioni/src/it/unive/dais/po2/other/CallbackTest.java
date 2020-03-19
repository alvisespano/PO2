package it.unive.dais.po2.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/*  INTERFACCIA FUNCTION
*   public interface Function<A, B> {
*       B apply(A x);
*   }
*/

/**
 * CALL BACK
 *     La callback è una funzione che viene passata a qualcun altro
 *     E' un oggetto che ha un metodo solo che si può chiamare.
 */
public class CallbackTest {

    /**
     * Funzione map generica polimorfa, ultra-riusabile, senza aver bisogno di un caso d'uso.
     * @param f Function
     * @param l Collection<A>
     * @param <A> Generics astratti dopo static
     * @param <B> Generics astratti dopo static
     * @return Collection<B>
     */
    /*
    * Generics astratti dopo static: public static <A, B>
    * ho un metodo che non sono generics sulla classe ma sono generics a questo metodo.
    *
    * Interfaccia Function, in Java, ha un solo metodo 'apply'.
    * Il metodo 'applay' fa le veci dell'applicazione di una funzione ad un oggetto.
    *
    * E' statat messa prima la Collection invece che la Function perché
    * quando si andrà a scrivere sul main la funzione, essendo sicuramente più lunga
    * rispetto alla Collection allora è meno comprensibile quest'ultima.
    */
    public static <A, B> Collection<B> map(Collection<A> l, Function<A, B> f) {
        Collection<B> c = new ArrayList<>();
        for(A a : l) { // per ogni elemento di tipo A contenuto in l che è una Collection<A>
            B b = f.apply(a);
            c.add(b);
            //c.add(f.apply(a));
        }
        return c;
    }
    /*
    *  In F#
    *  let rec map(f : 'a -> 'b) (l -> 'a list) : 'b list =
    *       match l with
    *       | [] -> []
    *       | x :: xs -> f x :: map f xs
    *
    */


    public static <X> void print(Collection<X> c) {
        map(c, new Function<X, Void>(){
            @Override
            public Void apply(X x) {
                System.out.println(x);
                return null;
            }
        });

    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            l.add(i);
        }

        /*
        *  Collection<Integer>: tipo valore di ritorno della map
        *  Chiamo il metodo map [statico, perché non usa niente della classe che lo contiene]
        *  gli passo:
        *       - l: ArrayList che è stata popolata dal for
        *       - Function<Integer, Integer>:
        *               oggetto fatto con un'anonymus class
        *               che nel corpo ha:
        *                 - @Override di un metodo dell'interfaccia e
        *                 - return: il valore che è stato passato come parametro incrementato di 1.
        */
       Collection<Integer> r = map(l, new Function<Integer, Integer>(){
            @Override
            public Integer apply(Integer x) {
                return x+1;
            }
        });

       /*
       *  Per stampare: faccio la map e gli passo la funzione che fa la stampa
       */

    }
}

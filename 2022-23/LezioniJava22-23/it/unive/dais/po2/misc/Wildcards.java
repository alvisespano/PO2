package it.unive.dais.po2.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Wildcards {

    // esempi di funzione identica
    public static <T> T identity(T x) {
        return x;
    }   // polimorfa tramite generics
    public static Object identity2(Object x) {
        return x;
    } // polimorfa tramite subtyping

    // esempi di wildcard
    public static Collection<?> w1(Collection<? extends Number> x) {
        return x;    // ? extends Number è sottotipo di ?
    }

    public static <X extends Number> Collection<?> w2(Collection<X> x) {
        return x;   // X è un Number, quindi è sottotipo di ?
    }

    public static Collection<?> w3(Collection<? super Number> x) {
        return x;  //
    }

    // riscriviamo ora la funzione di ordine superiore map() che avevamo scritto nel file HigherOrderFunctions.java
    // usiamo i wildcard per ampliare le potenzialità della funzione passata come secondo parametro
    // permettendo la controvarianza del dominio e la co-varianza del co-dominio
    public static <A, B> Collection<B> map(Collection<A> c,
                                           Function<? super A, ? extends B> f) {
        Collection<B> r = new ArrayList<>();
        for (A x : c) {
            r.add(f.apply(x));
        }
        return r;
    }


    public static void main(String[] args) {
        // esempio di chiamata della nuova versione della map()
        List<Integer> l = List.of(1, 2, 3);
        Collection<Number> u = map(l, (Number x) -> 1); // passiamo una lambda che ha tipo Function<Number, Integer> nonostante i generic A = Integer e B = Number

        // altri esempi con i wildcard: cosa è possibile fare e non fare
        List<? extends Zoo.Animal> l1 = new ArrayList<>();
        Zoo.Dog fido = new Zoo.Dog(30, "red");
        //l.add(fido);  // non si può chiamare add() perché Dog non è sottotipo di ? extends Animal
        Zoo.Animal pluto = l1.get(0);

        List<? super Zoo.Dog> l2 = new ArrayList<>();
        l2.add(fido);
        //Zoo.Dog rex = l2.get(0); // non si può gettare perché Dog non è supertipo di ? super Dog
    }


}

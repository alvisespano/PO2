package it.unive.dais.po2.aa2020_21.functional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TestFun {
/*
    // VERSIONE IN C: puntatori a funzione
    int f(int n, int(*g)(int)) {    // f ha tipo: int(*)(int, int(*)(int))
        return g(n);
    }

    int increment(int x) { return x + 1; }  // increment ha tipo: int(*)(int)

    int main() {
        int y = f(7, &increment);
    }

    // VERSIONE IN F#: funzioni come valori del primo ordine
    let f (n, g) = g(n)     // f : int * (int -> int) -> int

    let increment x = x + 1 // increment : int -> int

    let main () =
        let y = f(7, increment)
        let z = f(10, fun x -> x - 1)   // lambda
*/

    // le 4 forme di lambda (non serve definire le proprio, ci sono nel JDK)
    @FunctionalInterface
    public interface Function<A, B> {
        B apply(A x);
    }

    public interface Consumer<A> {
        void accept(A x);
    }

    public interface Supplier<A> {
        A get();
    }

    public interface Runnable {
        void run();
    }

    // altre functional interface
    @FunctionalInterface
    public interface BiFunction<A, B, R> {
        R apply(A x, B y);
    }

    public interface TriFunction<A, B, C, R>  {
        R apply(A x, B b, C c);
    }

    public interface QuadFunction<A, B, C, D, R> {
        R apply(A x, B b, C c, D d);
    }


    public static int f(int n, Function<Integer, Integer> g) {
        return g.apply(n);
    }


    private int delta = 4;

    public static int increment(int x) { return x + 1; }

    public int decrement(int x) { return x - this.delta; }

    public void increaseDelta() { ++this.delta; }

    public int miometodo(int a, String b, ArrayList<Collection<HashMap<Double, String>>> m) { return 0; }

    public int generateNum() { return 23; }

    private static class DecrementFun implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer x) {
            return x - 1;
        }
    }

    /* la map in F#
    let rec map f l =   // map : ('a -> 'b) -> 'a list -> 'b list
        match l with
        | [] -> []
        | x :: xs -> f x :: map f xs
     */

    public static <A, B> List<B> map(Iterable<A> c, Function<A, B> f) {   // POLIMORFISMO PARAMETRICO FIRST-CLASS
        List<B> r = new ArrayList<>();
        for(A a : c) r.add(f.apply(a));
        return r;
    }


    public static void main(String[] args) {

        Collection<String> a1 = new ArrayList<>();

        List<Integer> l1 = TestFun.map(a1, String::length);
        List<String> l2 = TestFun.map(a1, (s) -> s.concat("ciao"));



        int z = f(10, new DecrementFun());    // istanza di classe passata come argomento e subsunta al tipo Function<Integer, Integer>

        TestFun mytestfun = new TestFun();      // istanza di tipo TestFun

        // method reference
        Function<Integer, Integer> u = TestFun::increment;              // reference ad un metodo statico
        BiFunction<TestFun, Integer, Integer> u2 = TestFun::decrement;  // reference ad un metodo non-statico di un oggetto arbitrario di tipo TestFun
        Function<Integer, Integer> u3 = mytestfun::decrement;           // reference ad un metodo non-statico di un particolare oggetto di tipo TestFun

        // reference ad un metodo non-statico di un particolare oggetto di tipo TestFun: abbiamo solo i parametri visibili come type argument
        TriFunction<Integer, String, ArrayList<Collection<HashMap<Double, String>>>, Integer> u4 = mytestfun::miometodo;
        // reference ad un metodo non-statico di un oggetto arbitrario di tipo TestFun: abbiamo un parametro IN PIU' di tipo TestFun
        QuadFunction<TestFun, Integer, String, ArrayList<Collection<HashMap<Double, String>>>, Integer> u5 = TestFun::miometodo;

        Function<TestFun, Integer> j1 = TestFun::generateNum;   // reference ad un metodo non-statico di un oggetto arbitrario di tipo TestFun
        Supplier<Integer> j2 = mytestfun::generateNum;          // reference ad un metodo non-statico di un particolare oggetto di tipo TestFun

        Consumer<TestFun> k1 = TestFun::increaseDelta;  // reference ad un metodo non-statico di un oggetto arbitrario di tipo TestFun
        Runnable k2 = mytestfun::increaseDelta;         // reference ad un metodo non-statico di un particolare oggetto di tipo TestFun


        int y = f(7, TestFun::increment);                   // reference ad un metodo statico passato come Function<Integer,Integer>

        int z1 = f(10, new Function<Integer, Integer>() {    // anonymous class
            @Override
            public Integer apply(Integer x) {
                return x - 1;
            }
        });

        int z2 = f(10, (x) -> x - 1);                       // lambda

    }

}

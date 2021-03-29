package it.unive.dais.po2.aa2020_21.functional;

import javax.lang.model.type.IntersectionType;
import java.util.function.Function;

public class TestFun {
/*
    // VERSIONE IN C
    int f(int n, int(*g)(int)) {    // f ha tipo: int(*)(int, int(*)(int))
        return g(n);
    }

    int increment(int x) { return x + 1; }  // increment ha tipo: int(*)(int)

    int main() {
        int y = f(7, &increment);
    }

    // VERSIONE IN F#
    let f (n, g) = g(n)     // f : int * (int -> int) -> int

    let increment x = x + 1 // increment : int -> int

    let main () =
        let y = f(7, increment)
        let z = f(10, fun x -> x - 1)
*/

    // le 4 forme di lambda
    public interface Function<A, B> {
        B apply(A x);
    }

    public interface Consumer<A> {
        void apply(A x);
    }

    public interface Supplier<A> {
        A get();
    }

    public interface Runnable {
        void run();
    }

    public static int f(int n, Function<Integer, Integer> g) {
        return g.apply(n);
    }


    public static int increment(int x) { return x + 1; }

    private static class DecrementFun implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer x) {
            return x - 1;
        }
    }


    public static void main(String[] args) {
        int z = f(10, new DecrementFun());                  // named class

        int y = f(7, TestFun::increment);                   // method reference

        int z = f(10, new Function<Integer, Integer>() {    // anonymous class
            @Override
            public Integer apply(Integer x) {
                return x - 1;
            }
        });

        int z2 = f(10, (x) -> x - 1);                       // lambda
    }

}

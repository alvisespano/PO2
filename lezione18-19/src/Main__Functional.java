import zoo.Animale;
import zoo.Cane;
import zoo.Gatto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// riproduzione di alcune funzioni "notevoli" di F#
public class Main__Functional {


/*
let rec map f l =
    match l with
    | [] -> []
    | x :: xs -> f x :: map f xs
*/
// map : ('a -> 'b) -> 'a list -> 'b list

/*
let rec filter p l =
    match l with
    | [] -> []
    | x :: xs -> if p x then x :: filter p xs
                 else filter p xs
*/
// filter : ('a -> bool) -> 'a list -> 'a list

    // questa interfaccia è equivalente all'interfaccia java.util.Functional
    public interface Func<T, R> {
        R execute(T a);
    }

    public static <A> List<A> filter(List<A> l, Func<A, Boolean> p) {
        List<A> r = new ArrayList<>();
        for (A x : l) {
            if (p.execute(x)) r.add(x);
        }
        return r;
    }

    public static <A> void filter2(List<A> l, Func<A, Boolean> p) {
        // questo non funziona
        for (A a : l) {
            if (!p.execute(a)) l.remove(a);
        }

        // questo funziona perché chiama la remove() dell'iteratore
        Iterator<A> it = l.iterator();
        while (it.hasNext()) {
            A a = it.next();
            if (!p.execute(a)) it.remove();
        }

        // questo è ancora più conciso ed utilizza le nuove API funzionali
        l.removeIf(a -> !p.execute(a));

    }


    public static void main__filter() {
        List<Integer> ints = new ArrayList<>();
        ints.add(89);
        ints.add(34);
        ints.add(-16);
        ints.add(560);
        ints.add(-1);
        ints.add(46);

        List<Integer> r = filter(ints, new Func<Integer, Boolean>() {
            @Override
            public Boolean execute(Integer a) {
                return a >= 0;
            }
        });

        filter2(ints, new Func<Integer, Boolean>() {
            @Override
            public Boolean execute(Integer a) {
                return a >= 0;
            }
        });


    }


    // let ident x = x    // ident : 'a -> 'a

    public static Object ident__ugly(Object o) {
        return o;
    }

    public static <X> X ident(X x) {
        return x;
    }


    public static void main__ident() {
        Cane fido = new Cane();

        Cane c = (Cane) ident__ugly(fido);

        Cane c2 = ident(fido);
        Gatto g = ident(new Gatto());
    }


    public static <X, Y> List<Y> map(List<X> l, Func<X, Y> f) {
        List<Y> r = new ArrayList<>();
        for (X x : l) {
            r.add(f.execute(x));
        }
        return r;
    }



    public static void main__map() {
        List<String> strings = new ArrayList<>();
        strings.add("ciao");
        strings.add("pippo");
        strings.add("unive");

        List<Integer> r1 = map(strings, new Func<String, Integer>() {
            @Override
            public Integer execute(String a) {
                return a.length();
            }
        });


        List<Integer> r2 = new ArrayList<>();
        for (String s : strings) {
            r2.add(s.length());
        }


    }



    public static void main__wildcards() {

        List<? extends Animale> l = new ArrayList<Cane>();
        Animale a = l.get(0);



    }


}

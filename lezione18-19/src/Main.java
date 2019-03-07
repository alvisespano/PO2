import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static class Animale {
        public Cane m(Cane c) { return c; }
    }

    public static class Cane extends Animale {
        public void abbaia() {}
    }

    public static class Gatto extends Animale {
    }

    public static class PastoreTedesco extends Cane {}

    public static void main3() {
        Cane fido = new Cane();
        Cane turbo = new PastoreTedesco();
        Animale a = new PastoreTedesco();

        Cane c = fido.m(turbo);
        Cane c2 = a.m(fido);
        
    }



/*    let rec map f l =
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
        Iterator<A> it = l.iterator();
        while (it.hasNext()) {
            A a = it.next();
            if (!p.execute(a)) it.remove();
        }

        //for (A a : l) {
        //    if (!p.execute(a)) l.remove(a);
        //}
    }

    // let ident x = x    // 'a -> 'a

    public static Object ident__ugly(Object o) {
        return o;
    }

    public static <X> X ident(X x) {
        return x;
    }


    public static void main6() {
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


    public static void main5() {
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

    public static void main4() {
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



    public static void main2() {

        List<? extends Animale> l = new ArrayList<Cane>();
        Animale a = l.get(0);



    }


    public static class BackwardsArrayList<T> extends ArrayList<T> {

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int pos = BackwardsArrayList.this.size() - 1;
                @Override
                public boolean hasNext() {
                    return pos >= 0;
                }

                @Override
                public T next() {
                    return BackwardsArrayList.this.get(pos--);
                }
            };
        }
    }


    public static class PairwiseArrayList<E> extends ArrayList<E> {
        public Iterator<Pair<E, E>> pairiterator() {
            Iterator<E> it = super.iterator();
            return new Iterator<Pair<E, E>>() {
                private E a;
                @Override
                public boolean hasNext() {
                    a = it.next();
                    return it.hasNext();
                }

                @Override
                public Pair<E, E> next() {
//                    E a = it.next();
                    E b = it.next();
                    return new Pair<>(a, b);
                }
            };
        }
    }

    public static void main1() {
        Collection<Cane> cani = new BackwardsArrayList<>();
        cani.add(new Cane());
        cani.add(new PastoreTedesco());

        Iterator<Cane> it = cani.iterator();
        while (it.hasNext()) {
            Cane c = it.next();
            c.abbaia();
        }

    }




}

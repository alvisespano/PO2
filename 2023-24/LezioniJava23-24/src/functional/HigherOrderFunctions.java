package functional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;


/*
void for_each(int* a, size_t len, void(*f)(int)) {
    for (int i = 0; i < len; ++i)
        f(a[i]);
}

void print_int(int n) {
    printf("%d\n", n);
}

void main(){
    int A[10] = ...;
    for_each(A, 10, print_int);
}
*/

public class HigherOrderFunctions {
    public static <A, B> List<B> map(Iterable<A> c, Function<A, B> f) {
        List<B> r = new ArrayList<>();
        for (A x : c) {
            B b = f.apply(x);
            r.add(b);
        }
        return r;
    }
    public static <T> void forEach(Iterable<T> c, Consumer<T> f) {
        for (T x : c)
            f.accept(x);
    }
    public static <T> List<T> filter(Iterable<T> c, Predicate<T> f) {
        List<T> r = new ArrayList<>();
        for (T x : c) {
            if (f.test(x))
                r.add(x);
        }
        return r;
    }
    public static <T> void filter__impure(Iterable<T> c, Function<T, Boolean> f) {
        Iterator<T> it = c.iterator();
        while (it.hasNext()) {
            if (!f.apply(it.next()))
                it.remove();
        }

    }

    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, -3, 4);

        // map
        Collection<Boolean> r0 = map(l, x -> x > 0);
        Collection<Integer> r1 = map(l, x -> x + 1);
        Collection<Integer> r2 = map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });

        // forEach
        forEach(l, x -> System.out.println(x));
        forEach(l, new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.print(x);
            }
        });
        forEach(l, x -> System.out.println(x));
        forEach(l, new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.print(x);
            }
        });

        // filter
        Collection<Integer> c1 = filter(l, x -> x > 2);

        // filter__impure
        filter(l, x -> x > 2);
    }





}

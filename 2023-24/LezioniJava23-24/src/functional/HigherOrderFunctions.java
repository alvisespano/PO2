package functional;

import java.util.ArrayList;
import java.util.Collection;
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

    public static <A, B> Collection<B> map(Collection<A> c, Function<A, B> f) {
        Collection<B> r = new ArrayList<>();
        for (A x : c) {
            B b = f.apply(x);
            r.add(b);
        }
        return r;
    }

    public static void main2(String[] args) {
        List<Integer> l = List.of(1, 2, -3, 4);

        Collection<Boolean> r0 = map(l, x -> x > 0);

        Collection<Integer> r1 = map(l, x -> x + 1);
        Collection<Integer> r2 = map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });
    }

    public static <T> void forEach(Collection<T> c, Consumer<T> f) {
        for (T x : c) {
            f.accept(x);
        }
    }

    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3, 4);

        forEach(l, x -> { x = x + 1; });
        forEach(l, new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                if (x > 5)
                    x = x + 1;
            }
        });

        forEach(l, x -> System.out.println(x));
        forEach(l, new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.print(x);
            }
        });

    }


}

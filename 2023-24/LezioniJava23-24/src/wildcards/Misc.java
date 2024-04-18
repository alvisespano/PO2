package wildcards;

import functional.HigherOrderFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Misc {

    public static <A, B> List<B> map(Iterable<A> c,
                                     Function<? super A, ? extends B> f) {
        List<B> r = new ArrayList<>();
        for (A x : c) {
            B b = f.apply(x);
            r.add(b);
        }
        return r;
    }

    public static void main(String[] args) {
        List<String> l1 = List.of("pippo", "franco", "ciccio", "gigi");
        List<Number> l2 = map(l1, (CharSequence s) -> s.length());
    }

}

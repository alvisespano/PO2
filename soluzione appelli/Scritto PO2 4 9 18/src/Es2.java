import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Es2 {

    public static <A extends Comparable<B>, B> int compareMany(Collection<A> a, Collection<B> b) {
        Iterator<A> ia = a.iterator();
        Iterator<B> ib = b.iterator();
        int r = 0;
        while (ia.hasNext() && ib.hasNext()) {
            A x = ia.next();
            B y = ib.next();
            if ((r = x.compareTo(y)) != 0) break;
        }
        return ia.hasNext() && !ib.hasNext() ? 1 : !ia.hasNext() && ib.hasNext() ? -1 : r;
    }

}

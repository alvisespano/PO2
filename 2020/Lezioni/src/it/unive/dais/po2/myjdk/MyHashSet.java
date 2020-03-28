package it.unive.dais.po2.myjdk;

<<<<<<< HEAD
/**
 * Classe che fa veramente i set
 * non li fa l'interfaccia.
 * @param <T>
 */
=======
import java.util.function.Function;

>>>>>>> master
public class MyHashSet<T> extends MyLinkedSet<T> {
    private HashFun<T> h;

    public interface HashFun<E> {
        long hash(E e);
    }

    private static class DefaultHashFun<E> implements HashFun<E> {
        @Override
        public long hash(E e) {
            return e.hashCode();
        }
    }

    private class DefaultHashFun__nonstatic implements HashFun<T> {
        @Override
        public long hash(T e) {
            return e.hashCode();
        }
    }

    public MyHashSet(HashFun<T> h) {
        super();
        this.h = h;
    }


    public MyHashSet() {
        super();
        // lambda syntax
        this.h = (T x) -> x.hashCode();
        // method reference syntax
        this.h = T::hashCode;
        // anonymous class syntax
        this.h = new HashFun<T>() {
            @Override
            public long hash(T e) {
                return e.hashCode();
            }
        };
        // non-anonymous instance
        this.h = new DefaultHashFun<T>();
        // non-anonymous non-static instance
        this.h = new DefaultHashFun__nonstatic();
    }

    @Override
    public void add(T x) {
        MyIterator<T> it = iterator();
        boolean found = false;
        while (it.hasNext()) {
            T e = it.next();
            //if (x.hashCode() == e.hashCode())
            if (h.hash(x) == h.hash(e))
                found = true;
        }
        if (!found)
            l.add(x);
    }
}

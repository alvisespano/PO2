package it.unive.dais.po2.myjdk;

import java.util.function.Consumer;

public interface MyIterable<T> {

    MyIterator<T> iterator();

    default void forEach(Consumer<? super T> c) {
        MyIterator<T> it = this.iterator();
        while (it.hasNext()) {
            T x = it.next();
            c.accept(x);
        }
    }

}

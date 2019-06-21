import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Es2 {

    // esercizio 2
    public static class Node<T> implements Iterable<T> {

        public final @Nullable Node<T> left, right;
        public final @NotNull T data;

        public Node(@NotNull T data, @Nullable Node<T> l, @Nullable Node<T> r) {
            this.left = l;
            this.right = r;
            this.data = data;
        }

        public Node(@NotNull T data) {
            this(data, null, null);
        }

        protected static <T> void populate(@Nullable Node<T> n, @NotNull Collection<T> c) {
            if (n != null) {
                c.add(n.data);
                populate(n.left, c);
                populate(n.right, c);
            }
        }

        @NotNull
        @Override
        public Iterator<T> iterator() {
            Collection<T> c = new ArrayList<>();
            populate(this, c);
            return c.iterator();
        }
    }

    static class Prova {

        public class Node<T> implements Iterable<T> {
            Node<T> left, right;
            T val;
            Collection<Node<T>> col = new ArrayList<>();

            @Override
            public Iterator<T> iterator() {

                return new Iterator<>() {
                    Iterator<Node<T>> i = col.iterator();

                    public Node<T> func(Node<T> n) {
                        while (n.left != null)
                            col.add(func(n.left));
                        col.add(func(n.right));
                    }

                    @Override
                    public boolean hasNext() {
                        return iterator().hasNext();
                    }

                    @Override
                    public T next() {
                        return func(i.next());
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Node<Integer> root =
                new Node<>(1,
                        new Node<>(2,
                                new Node<>(3,
                                        new Node<>(4),
                                        new Node<>(5)),
                                new Node<>(6)),
                        new Node<>(7));

        for (int n : root) {
            System.out.println(n);
        }
    }

}

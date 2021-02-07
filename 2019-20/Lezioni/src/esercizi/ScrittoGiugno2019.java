package esercizi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ScrittoGiugno2019 {

    public static class Node<T> implements Iterable<T> {
        @NotNull
        private final T data;
        @Nullable
        private final Node<T> left, right;

        public Node(@NotNull T data, @Nullable Node<T> l, @Nullable Node<T> r) {
            this.data = data;
            left = l;
            right = r;
        }

        public Node(@NotNull T data) {
            this(data, null, null);
        }

        public Node(@NotNull T data, @NotNull Node<T> l) {
            this(data, l, null);
        }

        private void visit(@NotNull Collection<T> c) {
            c.add(data);
            if (left != null) left.visit(c);
            if (right != null) right.visit(c);
        }

        @NotNull
        @Override
        public Iterator<T> iterator() {
            Collection<T> r = new ArrayList<>();
            visit(r);
            return r.iterator();
        }

        @Override
        public String toString() {
            return String.format("%s(%s %s)",
                    data,
                    left == null ? "_" : left,
                    right == null ? "_" : right);
        }
    }


    public static void main(String[] args) {

        Node<Integer> l1 = new Node<>(2, new Node<>(4), new Node<>(5)),
                      r1 = new Node<>(3,
                              new Node<>(6,
                                      null,
                                      new Node<>(7))),
                    root = new Node<>(1, l1, r1);


        for (int i : root) {
            System.out.println(i);
        }

        System.out.println(root);

    }


}

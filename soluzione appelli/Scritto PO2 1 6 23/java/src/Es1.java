import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Es1 {

    // NOTA: la classe è statica solamente perché è nested per praticità
    public static class BST<T> implements Iterable<T> {
        @NotNull
        protected final Comparator<? super T> cmp;
        @Nullable
        protected Node root;

        public BST(@NotNull Comparator<? super T> cmp) {
            this.cmp = cmp;
        }

        public void insert(@NotNull T x) {
            root = insertRec(root, x);
        }

        // 1.a
        @NotNull
        protected Node insertRec(@Nullable Node n, @NotNull T x) {
            if (n == null)
                return new Node(x);
            int r = cmp.compare(x, n.data);
            if (r < 0)
                n.left = insertRec(n.left, x);
            else if (r > 0)
                n.right = insertRec(n.right, x);
            return n;
        }

        // 1.b.i
        protected void dfsInOrder(@Nullable Node n, @NotNull Collection<T> out) {
            if (n != null) {
                dfsInOrder(n.left, out);
                out.add(n.data);
                dfsInOrder(n.right, out);
            }
        }

        // 1.b.ii
        @NotNull
        @Override
        public Iterator<T> iterator() {
            Collection<T> c = new ArrayList<>();
            dfsInOrder(root, c);
            return c.iterator();
        }

        // 1.c
        @Nullable
        public T min() {
            if (root == null) return null;
            Node n = root;
            while(n.left != null) n = n.left;
            return n.data;
        }

        @Nullable
        public T max() {
            if (root == null) return null;
            Node n = root;
            while(n.right != null) n = n.right;
            return n.data;
        }


        protected class Node {
            @NotNull
            private final T data;
            @Nullable
            protected Node left, right;

            protected Node(@NotNull T data, @Nullable Node left, @Nullable Node right) {
                this.data = data;
                this.left = left;
                this.right = right;
            }

            protected Node(@NotNull T data) {
                this(data, null, null);
            }
        }

        @Override
        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (T x : this) {
                sb.append(x);
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    // 1.d
    public static class BST2<T extends Comparable<? super T>> extends BST<T> {
        public BST2() {
            super(Comparable::compareTo);
        }
    }

    public static void main(String[] args) {
        BST<Integer> t = new BST2<>();
        Random rnd = new Random();
        for (int i = 0; i < 20; ++i)
            t.insert(rnd.nextInt(100));
        System.out.println(t);
        System.out.printf("min = %d\nmax = %d\n", t.min(), t.max());
    }

}
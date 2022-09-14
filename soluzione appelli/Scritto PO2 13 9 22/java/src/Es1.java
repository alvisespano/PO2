// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 13/9/2022 per ciò che riguarda il quesito 1, ovvero l'esercizio in Java.
// Il quesito 2 riguardante C++ è in una solution per Visual Studio a parte.

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;

public class Es1 {

    // 1.d

    public static void main(String[] args) {
        TreeNode<Integer> t1 =
            TreeNode.lr(1,
                TreeNode.lr(2,
                    TreeNode.v(3),
                    TreeNode.v(4)),
                TreeNode.r(5,
                    TreeNode.lr(6,
                        TreeNode.v(7),
                        TreeNode.v(8))));

        TreeNode<Integer> t2 =
            TreeNode.lr(1,
                TreeNode.r(5,
                    TreeNode.lr(6,
                        TreeNode.v(7),
                        TreeNode.v(8))),
                TreeNode.lr(2,
                    TreeNode.v(3),
                    TreeNode.v(4)));


        // test pretty printer
        System.out.println("pretty printer:");
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);

        // test equality
        System.out.print("equality: ");
        System.out.println(t1.equals(t2) + ", " + t1.left.equals(t2.right));

        // test iterator
        System.out.print("iterator: ");
        for (Integer n : t1) {
            System.out.printf("%d ", n);
        }
        System.out.println();

    }

    public static class TreeNode<T> implements Iterable<T> {

        @NotNull
        private final T data;
        @Nullable
        private final TreeNode<T> left, right;
        @Nullable
        private TreeNode<T> parent = null;

        // 1.c

        public TreeNode(@NotNull T data, @Nullable TreeNode<T> left, @Nullable TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            if (left != null) left.parent = this;
            if (right != null) right.parent = this;
        }

        // i seguenti pseudo-costruttori aiutano a costruire alberi in modo più succinto e controllato rispetto all'innestamento dei costruttore

        // solo ramo sinistro
        public static <T> TreeNode<T> l(@NotNull T data, @NotNull TreeNode<T> left) {
            return new TreeNode<>(data, left, null);
        }

        // solo ramo destro
        public static <T> TreeNode<T> r(@NotNull T data, @NotNull TreeNode<T> right) {
            return new TreeNode<>(data, null, right);
        }

        // ramo sinistro e destro
        public static <T> TreeNode<T> lr(@NotNull T data, @NotNull TreeNode<T> left, @NotNull TreeNode<T> right) {
            return new TreeNode<>(data, left, right);
        }

        // foglia
        public static <T> TreeNode<T> v(@NotNull T data) {
            return new TreeNode<>(data, null, null);
        }

        // 1.b

        @Override
        public boolean equals(@Nullable Object o) {
            if (o instanceof TreeNode) {
                TreeNode<T> t = (TreeNode<T>) o;
                return areEqual(data, t.data) && areEqual(left, t.left) && areEqual(right, t.right);
            }
            return false;
        }

        // questo metodo ausiliaro non serve, basterebbe usare questo metodo del JDK: https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html#equals-java.lang.Object-java.lang.Object-
        private static <T> boolean areEqual(@Nullable Object a, @Nullable Object b) {
            return a == b || (a != null && a.equals(b));
            //return Objects.equals(a, b);
        }

        // 1.e

        @Override
        @NotNull
        public String toString() {
            return String.format("%s%s%s", data, left != null ? String.format("(%s)", left) : "", right != null ? String.format("[%s]", right) : "");
        }

        // 1.a

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                @Nullable
                private TreeNode<T> current = TreeNode.this;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Nullable
                private static <T> TreeNode<T> getNextNode(@NotNull TreeNode<T> n) {
                    if (n.left != null)
                        return n.left;
                    else if (n.right != null)
                        return n.right;
                    else {
                        while (n.parent != null) {
                            final TreeNode<T> last = n;
                            n = n.parent;
                            if (n.right != null && n.right != last)
                                return n.right;
                        }
                        return null;
                    }
                }

                @Override
                @NotNull
                public T next() {
                    T r = current.data;
                    current = getNextNode(current);
                    return r;
                }
            };
        }
    }
}

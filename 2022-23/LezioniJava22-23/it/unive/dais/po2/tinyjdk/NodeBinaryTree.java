package it.unive.dais.po2.tinyjdk;

public class NodeBinaryTree<T> implements BinaryTree<T> {

    private final T data;
    private final BinaryTree<T> l, r;

    public NodeBinaryTree(T data, BinaryTree<T> l, BinaryTree<T> r) {
        this.data = data;
        this.l = l;
        this.r = r;
    }

    public NodeBinaryTree(T data) {
        this(data, null, null);
    }

    public static <T> BinaryTree<T> L(T data, BinaryTree<T> l) {
        return new NodeBinaryTree<>(data, l, null);
    }

    public static <T> BinaryTree<T> R(T data, BinaryTree<T> r) {
        return new NodeBinaryTree<>(data, null, r);
    }

    public static void main(String[] args) {
        // TODO provare a riscrivere questo albero usando L() e R()
        NodeBinaryTree<Integer> root = new NodeBinaryTree<>(1,
                new NodeBinaryTree<>(8,
                        new NodeBinaryTree<>(9),
                        new NodeBinaryTree<>(11,
                                new NodeBinaryTree<>(12),
                                null)),
                new NodeBinaryTree<>(13)
                );
    }


    @Override
    public BinaryTree<T> left() {
        return null;
    }

    @Override
    public BinaryTree<T> right() {
        return null;
    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public BinaryTree<T> parent() {
        return null;
    }

    @Override
    public BinaryTree<T> root() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

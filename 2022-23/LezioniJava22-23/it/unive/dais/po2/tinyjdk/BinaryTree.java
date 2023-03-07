package it.unive.dais.po2.tinyjdk;

public interface BinaryTree<T> extends Iterable<T> {
    BinaryTree<T> left();
    BinaryTree<T> right();
    T get();
    int height();
    BinaryTree<T> parent();
    BinaryTree<T> root();
}

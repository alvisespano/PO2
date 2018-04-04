package it.unive.dais.po.tinylib;

// Questa classe è pressoché identica alla nested class statica MyStaticInnerIterator definita dentro MyNodeList.
// In effetti, una classe definita in un file è una classe implicitamente statica, perché non possiede enclosing class.
public class MyNodeListIterator<T> implements MyIterator<T> {

    private int pos = 0;
    private MyNodeList<T> enclosing;

    // Se questo costruttore fosse privato, come abbiamo fatto per la MyStaticInnerIterator, non potremmo
    // nemmeno invocarlo dai metodi di MyNodeList. Viceversa, se lo lasciamo pubblico, chiunque può costruire questa classe.
    // Se vogliamo mantenere la filosofia del "non permettere a nessuno di costruire un iteratore esplicitamente", allora il
    // massimo che possiamo fare è stringere la visibilità a package-private (che in Java si fa non specificando alcuna keyword).
    MyNodeListIterator(MyNodeList<T> enclosing) {
        this.enclosing = enclosing;
    }

    @Override
    public boolean hasNext() {
        return pos < enclosing.size();
    }

    @Override
    public T next() {
        try {
            return enclosing.getAt(pos++);
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MyIterator.next() failed");
        }
    }

}

package it.unive.dais.po.tinylib;

public interface MyList<U> extends MySequence<U> {

    void insertHead(U data);

    void insertAt(int position, U data) throws NotFoundException;

    void removeHead() throws NotFoundException;

    void removeAt(int position) throws NotFoundException;

    U getHead() throws NotFoundException;

}

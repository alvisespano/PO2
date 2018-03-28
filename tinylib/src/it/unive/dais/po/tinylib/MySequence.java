package it.unive.dais.po.tinylib;

public interface MySequence<U> extends MyIterable<U> {
    int size();
    U getAt(int position) throws NotFoundException;

}

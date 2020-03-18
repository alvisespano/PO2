package it.unive.dais.po2.myjdk;

public class OutOfBoundsException extends Exception {

    /**
     * Costruttore di OutOfBoundException con una stringa.
     * @param message messaggio
     */
    public OutOfBoundsException(String message) {
        super(message); // è già previsto da OutOfBoundExcepion

    }
}

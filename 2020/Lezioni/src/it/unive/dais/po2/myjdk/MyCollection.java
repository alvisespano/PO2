package it.unive.dais.po2.myjdk;

/*
* Le interfacce sono quello che un programma dichiara di avere.
* Le classi sono quello che uno ha veramente.
*/

/**
 * Interfaccia MyCollection
 * @param <T>
 */
public interface MyCollection<T> extends MyIterable<T> {

    void add(T x);
    int size();
    boolean contains(T x);
    boolean remove(T x);
    void clear();

    /**
     * Implementazione di default che dipende da size nell'interfaccia MyCollection
     * Le implmentazioni di DEFAULT dell'interfaccia permettono di
     * definire delle implementazioni delle implementazioni di default,
     * ovvero che sono totalmente polimorfe,
     * cioè dipendono completamente dall'esistenza di cose che non sono ancora state definite.
     * @return la dimensione della lista = 0 quindi vuota.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

<<<<<<< HEAD
=======
    /*
    * L'interfaccia non ha un metodo getter.
    * Quando uno ha una collection non può gettare,
    * quando uno ha una lista può gettare.
    */
>>>>>>> PO2
}

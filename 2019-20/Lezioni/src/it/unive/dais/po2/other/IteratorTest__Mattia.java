package it.unive.dais.po2.other;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest__Mattia {

    public static class MyArrayList<T> extends ArrayList<T> { /* eredito tutti i membri di ArrayList<T> */

        public enum IteratorKind {DEFAULT, EVEN, ODD}

        /*
         * Gli assegnamenti fatti in concomitanza con la dichiarazione dei field,
         * avvengono prima dell' inizializzazione del costruttore
         */
        private IteratorKind kind; /* private field di MyArrayList<T>*/

        /*
         * Costruttore definito affinchè sia possibile decidere in fase
         * di creazione dell'oggetto MyArrayList, quale comportamento dovrà
         * avere l'iteratore (nel momento del suo utilizzo)
         */
        public MyArrayList(IteratorKind kind) {
            this.kind = kind;
        }

        /* Costruttore di default, dichiarato esplicitamente (perchè dichiarato un altro costruttore)*/
        public MyArrayList() {
            /* assicura il corretto comportamento del metodo Next,
             * nel caso in cui non venga definito dall'utente un
             * comportamento speciale per l'iteratore in caso di costruzione
             * dell'oggetto MyArrayList<T>
             */
            this.kind = IteratorKind.DEFAULT;
        }


        /*
         * Overrido il metodo iterator dell'interfaccia Iterable (ereditata con ArrayList<T>)
         * affinchè venga ritornato il mio oggetto Iterator<T> (opportunamente modificato)
         * al posto dell'Iterator classico (ereditato) di ArrayList<T>*/
        @NotNull
        @Override
        public Iterator<T> iterator() {
            return new Iterator<>() {

                private int i = 0; /* private field della nuova */

                @Override
                public boolean hasNext() {
                    return i < size(); /* Zucchero sintattico per: MyArrayList.this.size(); */
                }

                @Override
                public T next() {
                    @Nullable T toReturn = null;

                    switch (kind) {
                        case DEFAULT:
                            toReturn = get(i++);
                            break;
                        case EVEN:
                            toReturn = get(i++);
                            if (hasNext())/* scarto il prossimo valore solo se questo è presente */
                                get(i++);
                            break;
                        case ODD:
                            get(i++);
                            if (hasNext()) /* scarto il prossimo valore solo se questo è presente */
                                toReturn = get(i++);
                    }

                    return toReturn;
                }
            };

        }

    }
}

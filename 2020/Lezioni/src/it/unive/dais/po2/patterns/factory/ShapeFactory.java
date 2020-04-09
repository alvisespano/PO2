package it.unive.dais.po2.patterns.factory;

import org.jetbrains.annotations.NotNull;

public class ShapeFactory {

    /**
     * Metodo che prende
     * @param name  nome forma
     * @param data  misure della forma
     * @return      l'implementazione della figura
     * @throws Exception ritornata perché la figura non è implementata o non presente
     */
    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0], data[1]);
        else if (name.toLowerCase().equals("circle"))
            return new Circle(data[0]);
        else throw new Exception("invalid shape: " + name);
    }


}
/*
 * VAR ARGOUMENTS: passo quanti argomenti voglio e lo leggo come fosse un array.
 * double... data
 * necessario in questo caso, in quanto non si vuole che il programmatore sappia
 * come tu rappresenti le cose, ma abbia solamene un'informazione pubblica (shape) e
 * non sappia altro come siano fatte le forme; ci specifica solamente il nome (String)
 * che shape vuole, poi ci specifica dei dati.
 *
 */

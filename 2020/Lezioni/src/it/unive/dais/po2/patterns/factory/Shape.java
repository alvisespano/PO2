package it.unive.dais.po2.patterns.factory;

/**
 * Interfaccia che definisce genericamente cos'è una figura.
 */
public interface Shape {
    /**
     * Metodo per disegnare una figura
     */
    void draw();

    /**
     * Metodo che calcola l'area della figura
     * @return
     */
    double area();

    /**
     * Metodo che calcola il perimetro della figura
     * @return
     */
    double perimeter();
}

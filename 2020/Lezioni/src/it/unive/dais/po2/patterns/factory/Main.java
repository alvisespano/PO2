package it.unive.dais.po2.patterns.factory;

public class Main {

    public static void main(String[] args) {
        try {
            // CREO LA FACTORY
            ShapeFactory factory = new ShapeFactoryWider(4.);

            // USO LA FACTORY
            Shape shape1 = factory.create("rectangle", 10, 11);
            Shape shape2 = factory.create("circle", 19);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*
 * PATTERN FACTORY
 * Una factory è una tecnica di programmazione che
 *  - nasconde i costruttori
 *  - permette il polimorfismo in costruzione.
 *
 * La factory è un pattern vecchio, con molte insicurezze.
 */
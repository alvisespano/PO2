package it.unive.dais.po2.patterns.factory;

public class Main {

    public static void main(String[] args) {
        try {
            ShapeFactory factory = new ShapeFactoryWider(4.);
            Shape shape1 = factory.create("rectangle", 10, 11);
            Shape shape2 = factory.create("circle", 19);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

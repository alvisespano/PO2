package it.unive.dais.po2.patterns.factory;

import org.jetbrains.annotations.NotNull;

public class ShapeFactory {

    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0], data[1]);
        else if (name.toLowerCase().equals("circle"))
            return new Circle(data[0]);
        else throw new Exception("invalid shape: " + name);
    }


}

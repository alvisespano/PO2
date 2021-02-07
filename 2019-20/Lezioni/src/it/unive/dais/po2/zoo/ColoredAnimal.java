package it.unive.dais.po2.zoo;

public abstract class ColoredAnimal extends Animal {
    protected String color;

    protected ColoredAnimal(int w, String c) {
        super(w);
        this.color = c;
    }

    public String getColor() { return color; }

}

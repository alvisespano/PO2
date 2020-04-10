package it.unive.dais.po2.zoo;


public class Cat extends ColoredAnimal {

    public Cat(int w, String c) {
        super(w, c);
    }

    @Override
    public void eat(Animal a) {
        weight += a.weight / 3;
        color = color + " fat";
    }

    public void meow() {
        System.out.println("miao!");
    }
}

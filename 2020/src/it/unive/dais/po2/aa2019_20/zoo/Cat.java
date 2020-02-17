package it.unive.dais.po2.aa2019_20.zoo;


public class Cat extends ColoredAnimal<Cat> {

    public Cat(int w, String c, Cat p) {
        super(w, c, p);
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

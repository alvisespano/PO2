package it.unive.dais.po2.zoo;

public class Dog extends ColoredAnimal<Dog> {

    public Dog(int w, String c, Dog p) {
        super(w, c, p);
    }


    @Override
    public void eat(Animal a) {
        weight += a.weight / 2;
    }


    public void bark() {
        System.out.println("bau!");
    }
}

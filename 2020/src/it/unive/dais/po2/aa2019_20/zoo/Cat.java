package it.unive.dais.po2.aa2019_20.zoo;


public class Cat extends Animal {
    private String color;

    public Cat(int w, String c, Cat p) {
        super(w, p);
        this.color = c;
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

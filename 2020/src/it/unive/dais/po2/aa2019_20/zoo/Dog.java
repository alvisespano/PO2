package it.unive.dais.po2.aa2019_20.zoo;

public class Dog extends Animal {
    private String color;   // TODO: fare una enum
    private Dog newpartner; // TODO: metterlo nella superclass con generic A <: Animal

    public Dog(int w, String c, Dog p) {
        super(w, p);
        this.newpartner = p;
        this.color = c;
    }


    @Override
    public void eat(Animal a) {
        weight += a.weight / 2;
    }

    @Override
    public Dog getPartner() {
        return newpartner;
    }

    public void bark() {
        System.out.println("bau!");
    }
}

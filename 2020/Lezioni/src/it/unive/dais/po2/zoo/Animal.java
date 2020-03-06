package it.unive.dais.po2.zoo;

public class Animal<A extends Animal> implements Creature {
    protected int weight;
    protected A partner;

    public Animal(int weight, A p) {
        this.weight = weight;
        this.partner = p;
    }

    public void eat(Animal a) {
        this.weight += a.weight;
    }

    public A getPartner() {
        return partner;
    }
}

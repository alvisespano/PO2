package it.unive.dais.po2.aa2019_20.zoo;

public class Animal implements Creature {
    protected int weight;
    protected Animal partner;

    public Animal(int weight, Animal p) {
        this.weight = weight;
        this.partner = p;
    }

    public void eat(Animal a) {
        this.weight += a.weight;
    }

    // TODO: rendere abstract questo metodo
    public Animal getPartner() {
        return partner;
    }
}

package it.unive.dais.po2.zoo;

import org.jetbrains.annotations.NotNull;

public class Animal implements Creature, Comparable<Animal> {
    protected int weight;

    public Animal(int weight) {
        this.weight = weight;
    }

    public int getWeight() { return weight; }

    public void eat(Animal a) {
        this.weight += a.weight;
    }

    @Override
    public int compareTo(@NotNull Animal o) {
        return this.weight - o.weight;
    }
}

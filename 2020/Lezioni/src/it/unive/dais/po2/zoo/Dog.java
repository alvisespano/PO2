package it.unive.dais.po2.zoo;

import org.jetbrains.annotations.NotNull;

public class Dog extends ColoredAnimal {
    private int bava;

    public Dog(int w, String c) {
        super(w, c);
        bava = w / 10;
    }

    @Override
    public void eat(Animal a) {
        weight += a.weight / 2;
    }

    public void bark() {
        System.out.println("bau!");
    }

    @Override
    public int compareTo(@NotNull Animal o) {
        if (o instanceof Dog) {
            Dog u = (Dog) o;
            return (this.bava + this.weight) - (o.weight + u.bava);
        }
        else {
            return super.compareTo(o);
        }
    }

    /**
     * Pretty printer: funzioni che stampano oggetti secondo un determinato formato
     * @return String.format
     */
    @Override
    public String toString() {
        return String.format("Dog[%d, %s]", weight, color);
    }
}

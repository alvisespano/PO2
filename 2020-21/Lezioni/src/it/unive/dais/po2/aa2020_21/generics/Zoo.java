package it.unive.dais.po2.aa2020_21.generics;

import java.util.ArrayList;
import java.util.Collection;

public class Zoo {

    public static class Animal {
        protected int weight;

        public void eat(Animal a) {
            this.weight += a.weight;
        }
    }

    public static class Humanoid extends Animal {
        public void marry(Humanoid d) {}
    }

    public static class Elf extends Humanoid {
        private int mana;
        public int getMana() { return mana; }
    }

    public static class Dwarf extends Humanoid {

    }

    public static class Dog extends Animal {
        public void bark() { }

        @Override
        public void eat(Animal a) {
            this.weight += a.weight / 2;
        }
    }

    public static class Cocker extends Dog {
        @Override
        public void eat(Animal a) {
            this.weight += a.weight / 3;
        }

    }

    public static class Cat extends Animal {
        public void meow() {
            System.out.println("meow");
        }
    }

    public static class Siamese extends Cat {
        @Override
        public void meow() {
            System.out.println("maauuuu");
        }
    }

    private static Dog makeAnimal() {
        return new Dog();
    }


    public static void main(String[] args) {

        Animal fido = makeAnimal();
        Animal dodi = new Dog();
        dodi = new Cat();
        Dog lilly = new Dog();
        fido.eat(lilly);        // SUBSUMPTION
        fido.eat(dodi);

        Humanoid john = new Humanoid();
        Humanoid alice = new Humanoid();
        Elf tara = new Elf();

        john.marry(tara);


        Collection<Dog> dogs = new ArrayList<Dog>();
        dogs.add(lilly);
        dogs.add(new Dog());
    }

}

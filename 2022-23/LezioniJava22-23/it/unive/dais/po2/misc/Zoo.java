package it.unive.dais.po2.misc;

public class Zoo {


    public static class Animal {
        protected int weight;

        public Animal(int w) {
            this.weight = w;
        }

        public void eat(Animal a) {
            this.weight += a.weight;
        }
    }

    public static class Dog extends Animal {
        private String hairColor;

        public Dog(int w, String hairColor) {
            super(w);
            this.hairColor = hairColor;
        }

        @Override
        public void eat(Animal a) {
            this.weight = a.weight * 2;
        }
    }

    public static void main(String[] args) {

        Animal fido = new Dog(50, "Red");
        Dog pluto = new Dog(20, "Dotted");
        Animal ciccio = pluto;
        Dog bobby = pluto;
        ciccio = bobby;

        ciccio.eat(new Dog(10, "White"));

    }

}

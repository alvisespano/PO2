package misc.po2.unive.it;

public class Zoo {

    public static class Animal {

        protected int weight;

        public Animal(int weight) {
            this.weight = weight;
        }

        public void eat(Animal a) {
            this.weight += a.weight;
        }
    }

    public static class Dog extends Animal {
        private String owner;

        public Dog(int weight, String owner) {
            super(weight);
            this.owner = owner;
        }

        public void bark() {
            System.out.println("BAUUU!");
        }

        @Override
        public void eat(Animal a) {
            this.weight += a.weight * 2;
        }
    }

    public static class Cat extends Animal {
        public Cat(int weight) {
            super(weight);
        }

        public void meow() {
            System.out.println("Meow!");
        }

        @Override
        public void eat(Animal a) {
            this.weight += a.weight / 2;
        }
    }

    // POLIMORFISMO SUBTYPING
    public static void main(String[] args) {
        Animal tilde = new Animal(10);
        Dog fido = new Dog(20, "Gigi");  // SUBSUMPTION
        fido.eat(tilde);
        Animal pippo = new Cat(30);
        //pippo.meow();
        pippo.eat(fido);
    }

}

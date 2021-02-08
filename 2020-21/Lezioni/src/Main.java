
public class Main {

    public static class Animal {
        protected int weight;

        public void eat(Animal a) {
            this.weight += a.weight;
        }
    }

    public static class Dog extends Animal {
        public void bark() { }

        public void marry(Dog d) {}

        @Override
        public void eat(Animal a) {
            this.weight += a.weight / 2;
        }
    }

    private static Animal makeAnimal() {
        return new Dog();
    }

    public static void main(String[] args) {

        Animal fido = makeAnimal();
        Dog lilly = new Dog();
        fido.eat(lilly);        // SUBSUMPTION

        lilly.marry(fido);      // non compila perché fido : Animal

    }

}

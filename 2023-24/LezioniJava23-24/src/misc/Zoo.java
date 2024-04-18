package misc;

public class Zoo {

    public static class Animal {
        protected int weight;

        public int getWeight() { return weight; }

        public Animal(int w) {
            this.weight = w;
        }

        public void eat(Animal a) {
            this.weight += a.weight;
        }
    }

    public static class Dog extends Animal {
        private boolean pedgree;

        public Dog(int w, boolean ped) {
            super(w);
            this.pedgree = ped;
        }

        public void bark() {
            System.out.println("bau!");
        }

        public void eat(Animal a) {
            this.weight += a.weight * 2;
        }
    }

    public static class Cat extends Animal {

        public Cat(int w) {
            super(w);
        }

        @Override
        public void eat(Animal a) {
            this.weight += a.weight / 2;
        }
    }

    public static void main(String[] args)
    {
        Dog fido = new Dog(50, false);
        Animal pluto = new Dog(40, true);
        pluto.eat(fido);
    }

}
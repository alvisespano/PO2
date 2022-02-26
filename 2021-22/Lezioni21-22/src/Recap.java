public class Recap {

    private int x;
    private static float y = Recap.z;
    public static float z;

    protected void m() {
        this.x = 8;
    }
    public static void n() {
        Recap.y = 8;
    }

    private class Boo {}

    public static class Animal {
        protected int peso;
        public Animal(int peso) {
            this.peso = peso;
        }
        public void eat(Animal a) {
            this.peso += a.peso;
        }
    }

    public static class Dog extends Animal {

        public Dog(int peso) {
            super(peso);
        }

        @Override
        public void eat(Animal a) {
            this.peso += a.peso / 2;
        }
    }

    public static class Labrador extends Dog {

        public Labrador(int peso) {
            super(peso);
        }

        @Override
        public void eat(Animal a) {
            this.peso += a.peso * 2;
        }

        public void drool() {}

    }

    public static void main(String[] args) {
        Animal a = new Labrador(15);
        a.eat(new Dog(10));

    }


}

import java.util.*;
import java.util.function.Function;

public class Sorting {

    public static class Humanoid implements Comparable<Humanoid> {
        protected int health;

        public Humanoid(int h) {
            health = h;
        }

        @Override
        public int compareTo(Humanoid o) {
            return health - o.health;
        }

        @Override
        public String toString() {
            return String.format("Humanoid[health=%d]", health);
        }
    }

    public static class Elf extends Humanoid {
        private int mana;

        public Elf(int health, int mana) {
            super(health);
            this.mana = mana;
        }

        @Override
        public int compareTo(Humanoid o) {
            if (o instanceof Elf) {
                Elf e = (Elf) o;
                return mana - e.mana;
            }
            return super.compareTo(o);
        }

        @Override
        public String toString() {
            return String.format("Elf[health=%d;mana=%d]", health, mana);
        }

    }

    public static class Creature {
    }

    public static class Animal extends Creature {
        protected int weight;

        public Animal(int w) {
            weight = w;
        }

        public void eat(Animal a) {
        }

        public Animal spawn() {
            return new Animal(weight / 3);
        }
    }

    public static class Dog extends Animal {
        private int tail;

        public Dog(int w, int tail) {
            super(w);
            this.tail = tail;
        }

        public void bark() {
        }

        @Override
        public void eat(Animal a) {
        }

        @Override
        public Dog spawn() {
            return new Dog(weight / 2, 1);
        }
    }

    static class MyComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.weight - o2.weight;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        {
            List<Dog> a = new ArrayList<>();
            for (int i = 0; i < 10; ++i)
                a.add(new Dog(i * 10, i));

            // non-anonymous class
            sort(a, new MyComparator());
            // anonymous class
            sort(a, new Comparator<Animal>() {
                @Override
                public int compare(Animal o1, Animal o2) {
                    return o1.weight - o2.weight;
                }
            });
            // lambda che si converte in una anonymous class
            sort(a, (o1, o2) -> o1.weight - o2.weight);
        }

        {
            List<Elf> l = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
//            if (rand.nextBoolean())
                l.add(new Elf(rand.nextInt(100), rand.nextInt(50)));
                //          else
                //            l.add(new Humanoid(rand.nextInt(100)));
            }

            System.out.println(l);
            sort(l);
            System.out.println(l);
        }
    }

    public static <T extends Comparable<? super T>> void sort(List<T> l) {
        Collections.sort(l);
    }

    public static <T> void sort(List<T> l, Comparator<? super T> c) {
        Collections.sort(l, c);
    }
}

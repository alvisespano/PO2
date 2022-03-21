import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sorting {

    public static class Humanoid implements Comparable<Humanoid> {
        protected int health;

        public Humanoid(int h) {
            health = h;
        }

        @Override
        public int compareTo(Humanoid o) {
            return -(health - o.health);
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
        public String toString() {
            return String.format("Elf[health=%d;mana=%d]", health, mana);
        }

    }


    public static void main(String[] args) {
        Random rand = new Random();
        List<Elf> l = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            //if (rand.nextBoolean())
            //    l.add(new Elf(rand.nextInt(100), rand.nextInt(50)));
            //else
                l.add(new Humanoid(rand.nextInt(100)));
        }

        System.out.println(l);
        sort(l);
        System.out.println(l);
    }

    public static <T extends Comparable<? super T>> void sort(List<T> l) {
        Collections.sort(l);
    }
}

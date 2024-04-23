package misc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static <T> void sort(List<T> list, Comparator<? super T> c) { ... }

    public static class Animal {
        public int weight;
        public Animal(int w) { this.weight = w; }
    }
    public static class Dog extends Animal {
        public boolean pedigree;
        public Dog(int w, boolean p) { super(w); this.pedigree = p; }
    }

    public static void main(String[] args) {
        List<Dog> l = new ArrayList<>();
        l.add(new Dog(50, false));
        l.add(new Dog(20, true));
        l.add(new Dog(30, true));

        sort(l, new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.weight - o2.weight;
            }
        });
    }

}

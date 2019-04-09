package collections;

import java.util.*;

public class Main {

    public static class Plant {
        private int height;

    }

    public static class Animal implements Comparable<Animal> {
        private int weight;
        private String name;

        public Animal(String name, int w) {
            this.name = name;
            this.weight = w;
        }

        public int getWeight() { return weight; }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Animal o) {
            return this.weight - o.weight;
        }

        @Override
        public int hashCode() {
            return weight * name.hashCode();
        }

    }

    public static class Dog extends Animal {

        public Dog(String name, int w) {
            super(name, w);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public static void main3() {

        Map<Dog, String> m = new HashMap<>();
        Dog emma = new Dog("emma", 10);
        Dog toby = new Dog("toby", 10);
        Dog bob = new Dog("bob", 20);

        m.put(emma, "cecilia");
        m.put(toby, "mihail");
        m.put(bob, "alex");
    }





    public static void main2() {
        MyCollection<Pair<String, Integer>> rubrica = new MyListMap<>();
        rubrica.add(new Pair<>("Alvise", 34712345));
        rubrica.add(new Pair<>("Diego", 987654321));
    }


    public static void main(String[] args) {

        List<Animal> a = new ArrayList<>();
        Collections.sort(a);

        List<Plant> b = new ArrayList<>();
        Collections.sort(b, new Comparator<Plant>() {
            @Override
            public int compare(Plant x, Plant y) {
                return x.height - y.height;
            }
        });

    }
}

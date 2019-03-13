package it.unive.dais.po.appello.primo_24_5_18;

public class HannaBarbera {

    // classi date dal testo

    public interface Food {
        int getWeight();
    }

    public interface Animal extends Food {
        void eat(Food f);
    }

    public static class Mouse implements Animal {
        private int weight;

        public Mouse(int weight) { this.weight = weight; }

        @Override
        public void eat(Food f) { weight += f.getWeight() / 3; }    // tema A: 2

        @Override
        public int getWeight() { return weight; }
    }

    public static class Cat implements Animal {
        private int weight;

        public Cat(int weight) { this.weight = weight; }

        @Override
        public void eat(Food f) { weight += f.getWeight() / 10; }    // tema A: 5

        @Override
        public int getWeight() { return weight; }
    }

    public static class Cheese implements Food {
        @Override
        public int getWeight() { return 300; }
    }


    // es 1.a
    private static class Refactored {        // mettiamo la versione rifattorizzata dentro una classe statica per evitare conflitti coi nomi delle classi

        // le interfacce Food e Animal non serve rifattorizzarle, sono a posto così

        public abstract class AbstractAnimal implements Animal {
            private int weight, div;

            protected AbstractAnimal(int weight, int div) { this.weight = weight; }

            @Override
            public void eat(Food f) { weight += f.getWeight() / div; }

            @Override
            public int getWeight() { return weight; }
        }

        public class Cat extends AbstractAnimal {
            protected Cat(int weight, int div) {
                super(weight, 5);   // valori del tema A
            }
        }

        public class Mouse extends AbstractAnimal {
            protected Mouse(int weight, int div) {
                super(weight, 2);
            }
        }
    }

    public static void main(String[] args) {
        Animal tom = new Cat(200);     // costanti del tema A
        Animal jerry = new Mouse(10);
        jerry.eat(new Cheese());
        jerry.eat(new Food() {
            @Override
            public int getWeight() { return 10; }
        });
        tom.eat(jerry);
        System.out.println(String.format("Tom now weights %d", tom.getWeight()));
    }
}



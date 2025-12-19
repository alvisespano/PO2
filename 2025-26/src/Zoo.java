

/*struct S {
   int n;
   float x;
}

struct U {
    S parent;
    char* s;
}

void f(S this) {
    if (this.n > 5)
}

int m(U this) { return this.parent.n + strlen(this.s)  }


public class S {
    public int n;
    public float x;

    public void f() {
        if (this.n > 5) { ... }
    }
}

public class U extends S {
    public String s;
    public int m() { return n + s.length(); }

}
*/


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
        private String hair;

        public Dog(int weight, String hair) {
            super(weight);
            this.hair = hair;
        }

        @Override
        public void eat(Animal a) {
            this.weight += a.weight / 3;
        }

        public String getHair() { return hair;}
    }

    public static class Cat extends Animal {
        public Cat(int weight) {
            super(weight);
        }

        public void eat(Animal a) {
            this.weight += a.weight * 3;
        }
    }

    public static void main(String[] args) {
        Animal pippo = new Dog(5, "liscio");
        Dog gigio = new Dog(7, "riccio");
        pippo.eat(gigio);

        Animal romeo = new Cat(40);
        romeo.eat(gigio);

    }
}
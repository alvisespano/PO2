public class Generics {


    public static class FloatPair {
        private float a, b;

        public FloatPair(float a, float b) {
            this.a = a;
            this.b = b;
        }

        public float getFirst() { return a; }
        public float getSecond() { return b; }

        public void setFirst(float a) {
            this.a = a;
        }
        public void setSecond(float b) {
            this.b = b;
        }

    }

    public static class Pair<A, B> {
        private A a;
        private B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getFirst() { return a; }
        public B getSecond() { return b; }

        public void setFirst(A a) {
            this.a = a;
        }
        public void setSecond(B b) {
            this.b = b;
        }

    }

    public static int f(int x) {
        return x + 1;
    }


    public static void main(String[] args) {
        FloatPair p1 = new FloatPair(23.11f, 67.69f);
        Pair<Float, Float> p2 = new Pair<>(23.11f, 67.69f);
        Pair<String, Zoo.Dog> p3 = new Pair<>("ciao", new Zoo.Dog(10));
        Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> p4 = new Pair<>(new Pair<>(1, 2), new Pair<>(5, 6));
        Pair<String, Zoo.Dog> p5 = new Pair<>("ciao", new Zoo.Labrador(10));

    }



}

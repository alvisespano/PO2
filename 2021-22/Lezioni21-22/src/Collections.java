import java.util.*;

public class Collections {

    public static class RandomSequence implements Iterable<Integer> {

        private final int sz;

        public RandomSequence(int sz) {
            this.sz = sz;
        }

        private static class myIterator implements Iterator<Integer> {
            private int cnt = 0;
            private Random rnd = new Random(1123);
            private RandomSequence that;

            private myIterator(RandomSequence that) {
                this.that = that;
            }

            @Override
            public boolean hasNext() {
                return this.cnt < that.sz;
            }

            @Override
            public Integer next() {
                ++cnt;
                return rnd.nextInt();
            }
        }

        @Override
        public Iterator<Integer> iterator() {
            return new myIterator(this);
        }

//        @Override
        public Iterator<Integer> iterator__() {
            return new Iterator<Integer>() {
                private int cnt = 0;
                private Random rnd = new Random(1123);

                @Override
                public boolean hasNext() {
                    return cnt < sz;
                }

                @Override
                public Integer next() {
                    ++cnt;
                    return rnd.nextInt();
                }
            };
        }
    }

    public interface I {
        void m();
        int p(int x);
    }

    public static class C implements I {
        @Override
        public void m() {
            System.out.println("ciao");
        }

        @Override
        public int p(int x) {
            return x * 2;
        }
    }

    public static void main(String[] args) {

        I y = new C();

        I x = new I() {

            @Override
            public void m() {
                System.out.println("ciao");
            }

            @Override
            public int p(int x) {
                return x * 2;
            }
        };

        for (int n : new RandomSequence(80)) {
            System.out.println(n);
        }

        RandomSequence o = new RandomSequence(80);
        Iterator<Integer> it = o.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }

    }

}

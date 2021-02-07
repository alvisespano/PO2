package esercizi;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Random;

public class Es_RandomIterator {

    public static void main2(String[] args) {
        Iterator<Integer> it = new RandomIterator(10, -50, 20);
        int cnt = 0;
        while(it.hasNext()) {
            int n = it.next();
            System.out.println(String.format("#%d: %d", cnt++, n));
        }
    }

    public static void main(String[] args) {
        int cnt = 0;
        for (int n : new RandomSequence(100)) {
            System.out.println(String.format("#%d: %d", cnt++, n));
        }
    }

    private static class RandomSequence implements Iterable<Integer> {
        private int amount;

        public RandomSequence(int amount) {
            this.amount = amount;
        }

        @NotNull
        @Override
        public Iterator<Integer> iterator() {
            return new RandomIterator(amount, 0, 100);
        }
    }


    private static class RandomIterator implements Iterator<Integer> {
        private static Random rnd = new Random();
        private int amount, a, b;

        public RandomIterator(int amount, int a, int b) {
            assert b > a;
            this.amount = amount;
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean hasNext() {
            return amount > 0;
        }

        @Override
        public Integer next() {
            --amount;
            return rnd.nextInt(b - a + 1) + a;
        }
    }


}

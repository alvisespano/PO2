import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Es3 {

    // 3.a
    public static class FactThread extends Thread {

        private int n, result;

        public FactThread(int n) {
            this.n = n;
        }

        public int getResult() {
            return result;
        }

    }

    // 3.b
    public static Collection<FactThread> multiFact(Collection<Integer> ns) {
        Collection<FactThread> r = new ArrayList<>();
        for (int n : ns) {
            r.add(new FactThread(n));
        }
        return r;
    }

    // 3.c
    public static void main(String[] args) {
        for (FactThread t : multiFact(Arrays.asList(1, 2, 3, 4, 5, 6))) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s calculated %d", t, t.getResult()));
        }
    }

}

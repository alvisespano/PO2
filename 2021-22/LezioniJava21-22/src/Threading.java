import java.util.Random;

public class Threading {

    private static Random rand = new Random();

    private static void doSomething() {
        long ms = rand.nextLong(500L);
        Thread self = Thread.currentThread();
        for (int i = 0; i < 20; ++i) {
            System.out.printf("%s[%d] #%d\n", self.getName(), self.getId(), i);
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            doSomething();
        });
        t1.start();

        doSomething();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

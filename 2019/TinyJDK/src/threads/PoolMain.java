package threads;

import java.util.Random;
import java.util.concurrent.TimeoutException;

public class PoolMain {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(10, 3);
        while (true) {
            ThreadPool.print("%s", pool);
            try {
                Thread t = pool.acquire(() -> {
                    final long now = System.currentTimeMillis();
                    sleep(500);
                    ThreadPool.print("took %d ms", System.currentTimeMillis() - now);
                });
                sleep(100);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    private static Random rand = new Random();

    private static void sleep(int ms) {
        try {
            Thread.sleep(rand.nextInt(ms));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

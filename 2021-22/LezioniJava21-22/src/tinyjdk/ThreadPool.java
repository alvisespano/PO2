package tinyjdk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private BlockingQueue<PooledThread> q = new LinkedBlockingQueue<>();

    public class PooledThread extends Thread {

        @Nullable
        private Runnable myRunnable;

        @Override
        public void run() {
            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (myRunnable != null) {
                    myRunnable.run();
                    q.add(this);
                }
            }
        }

        private void setRunnable(@NotNull Runnable r) {
            myRunnable = r;
            notify();
        }
    }

    public ThreadPool(int capacity) {
        for (int i = 0; i < capacity; ++i) {
            PooledThread t = new PooledThread();
            t.start();
            q.add(t);
        }
    }

    public PooledThread acquire(Runnable r) throws InterruptedException {
        PooledThread t = q.take();
        // TODO: spawnare thread nuovi se la coda è vuota
        t.setRunnable(r);
        return t;
        // TODO: scorrere la pool e buttare via thread in eccesso se non usati
    }




    public static void main(String[] args) {
        try {
            ThreadPool pool = new ThreadPool(20);
            PooledThread t1 = pool.acquire(() -> { for (int i = 0; i < 10; ++i) System.out.println(i); });

            PooledThread t2 = pool.acquire(() -> { for (int i = 0; i < 100; ++i) System.out.println(i); });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

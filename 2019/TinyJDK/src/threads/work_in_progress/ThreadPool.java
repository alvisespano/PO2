package threads.work_in_progress;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {
    private Queue<PooledThread> q = new ConcurrentLinkedQueue<>();

    public static class PooledThread extends Thread {
        @Nullable
        private Runnable currentRunnable;

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (this) {
                        print("waiting...");
                        wait();
                        print("awaken");
                    }
                    Objects.requireNonNull(currentRunnable).run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public String toString() {
            return String.format("[%s:%d]", this.getName(), this.getId());
        }

    }

    public PooledThread acquire(Runnable cb) {
        @NotNull final PooledThread p;
        if (q.isEmpty()) {
            System.out.println("spawning");
            p = new PooledThread();
            p.start();
        } else {
            p = q.poll();
        }
        synchronized (p) {
            print("notify");
            p.currentRunnable = cb;
            p.notify();
            return p;
        }
    }

    public void release(PooledThread t) {
        q.add(t);
    }


    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        Thread t1 = pool.acquire(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("bye");
        });
    }

    private static void print(String s) {
        System.out.println(String.format("%s: %s", Thread.currentThread(), s));
    }
}
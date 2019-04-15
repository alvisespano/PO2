package threads;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadPool {
    private static final long DEFAULT_ACQUIRE_TIMEOUT_MS = 500L;
    @NotNull
    private final BlockingQueue<PooledThread> q;
    private final int max;
    private int total = 0;

    @Override
    public String toString() {
        return String.format("ThreadPool[available:%d total:%d max:%d]", available(), total(), max);
    }

    private synchronized void incrementTotal() {
        ++total;
    }

    private synchronized void decrementTotal() {
        --total;
    }

    public ThreadPool(int max, int prespawn) {
        q = new LinkedBlockingQueue<>(max);
        this.max = max;
        for (int i = 0; i < Math.min(max, prespawn); ++i) {
            q.add(spawn());
        }
    }

    public ThreadPool(int max) {
        this(max, 0);
    }

    private static void print(String s) {
        System.out.println(String.format("%s: %s", Thread.currentThread(), s));
    }

    public static void print(String fmt, Object... o) {
        print(String.format(fmt, o));
    }

    protected class PooledThread extends Thread {
        @Nullable
        private Runnable customRunnable;

        @Override
        public String toString() {
            return String.format("[%s:%d]", getName(), getId());
        }

        @Override
        public void run() {
            incrementTotal();
            try {
                while (true) {
//                    print("entering wait state...");
                    @NotNull Runnable r;
                    synchronized (this) {
                        wait();
                        r = Objects.requireNonNull(customRunnable);
//                        print("awaken: executing custom runnable...");
                    }
                    try {
                        r.run();
                    } catch (Throwable e) {
                        print("exception caught during execution of custom runnable");
                        e.printStackTrace();
                    }
                    synchronized (this) {
                        customRunnable = null;
                    }
                    if (!q.offer(this)) {
                        print("cannot requeue due to space limit");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                print("interrupted exception");
                e.printStackTrace();
            }
            print("exiting");
            decrementTotal();
        }
    }

    public int available() {
        return q.size();
    }

    public synchronized int total() {
        return total;
    }

    @NotNull
    private PooledThread spawn() {
        PooledThread p = new PooledThread();
        p.start();
        print("spawned new thread %s", p);
        return p;
    }

    @NotNull
    public Thread acquire(@NotNull Runnable cb, long time, @NotNull TimeUnit unit) throws InterruptedException, TimeoutException {
        @NotNull final PooledThread p;
        if (q.isEmpty()) {
            if (total() < max)
                p = spawn();
            else {
                print("pool max reached: waiting for an available thread...");
                PooledThread tmp = q.poll(time, unit);
                if (tmp == null)
                    throw new TimeoutException();
                p = tmp;
            }
        } else {
            p = q.poll();
        }
        synchronized (p) {
            p.customRunnable = cb;
            p.notify();
        }
        return p;
    }

    @NotNull
    public Thread acquire(@NotNull Runnable cb) throws InterruptedException, TimeoutException {
        return acquire(cb, DEFAULT_ACQUIRE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }

}

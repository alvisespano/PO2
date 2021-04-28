package it.unive.dais.po2.aa2020_21.patterns;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThreadPool {

    private final List<PooledThread> threads = new ArrayList<>();
    private long timeout = 5L * 60 * 1000;

    public ThreadPool() {}

    public ThreadPool(int prespawn) {
        // TODO
    }

    public void setTimeoutInMillis(long tm) {
        timeout = tm;
    }

    // COME SI DEZUCCHERA IL COSTRUTTO SYNCHRONIZED
    /*public synchronized void m1() {
        synchronized (this) {
            // blocco
        }
    }
    public static synchronized void m2() {
        Class<?> c = ThreadPool.class;
        synchronized (c) {
            // blocco
        }
    }*/

    private class PooledThread extends Thread {
        @NotNull
        private Runnable r;

        private long lastUsed = System.currentTimeMillis();

        public PooledThread(Runnable r) {
            this.r = r;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    r.run();
                } catch (Exception e) {
                    System.out.printf("%s: exception caught\n", this);
                }
                synchronized (threads) {
                    // threads.lock()
                    // try {
                    threads.add(this);
                    lastUsed = System.currentTimeMillis();
                    // }
                    // finally { threads.unlock() }
                }

                // RESOURCE ALLOCATOR
                /*
                    PREAMBOLO               ad esempio lock
                    try { BLOCCO }          ad esempio la critical section
                    finally { EPILOGO }     ad esempio unlock
                */
                try {
                    wait(); // TODO controllare se la notify() può accadere prima della wait()
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(String.format("%s: interrupted. Aborting...", this));
                }
            }
        }

        @Override
        public String toString() {
            return String.format("thread#%d[%s]", getId(), getName());
        }

        public void setRunnable(Runnable r) {
            this.r = r;
        }

        public boolean hasExpired() {
            long now = System.currentTimeMillis();
            return now - lastUsed > timeout;
        }
    }

    @NotNull
    public Thread acquire(@NotNull Runnable r) {
        PooledThread t;
        synchronized (threads) {
            threads.removeIf(PooledThread::hasExpired);
            if (threads.isEmpty()) {
                t = new PooledThread(r);
                t.start();
            } else {
                t = threads.remove(0);
                t.setRunnable(r);
                t.notify();
            }
        }
        return t;
    }

    public static void main(String[] args) {

        ThreadPool pool = new ThreadPool();

        Thread t = pool.acquire(() -> {
            for (int i = 0; i < 10; ++i) {
                Thread self = Thread.currentThread();
                System.out.printf("%s[%d]: %d", self.getName(), self.getId(), i);
            }
        });

        // TODO testare la pool


    }

}

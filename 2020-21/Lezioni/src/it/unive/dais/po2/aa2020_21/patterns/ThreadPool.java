package it.unive.dais.po2.aa2020_21.patterns;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private final List<PooledThread> threads = new ArrayList<>();

    public ThreadPool() {}

    public ThreadPool(int prespawn) {
        // TODO
    }

    private class PooledThread extends Thread {
        @NotNull
        private Runnable r;

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
                threads.add(this);
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
    }

    @NotNull
    public Thread acquire(@NotNull Runnable r) {
        PooledThread t;
        if (threads.isEmpty()) {
            t = new PooledThread(r);
            t.start();
        }
        else {
            // TODO critical section
            t = threads.remove(0);
            t.setRunnable(r);
            t.notify();
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

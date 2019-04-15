package threads.work_in_progress;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {
    private Queue<PooledThread> q = new ConcurrentLinkedQueue<>();

    public static class PooledThread extends Thread {
        private Runnable r;
    }

    public PooledThread acquire(Runnable cb) {
        if (q.isEmpty()) {
            return new PooledThread();
        } else {
            PooledThread p = q.poll();
            p.notify();
            return p;
        }
    }

    public void release(PooledThread t) {
        q.add(t);
    }

    public static void threadMain(PooledThread p) {
        while (true) {
            try {
                p.wait();
                p.r.run();  // TODO: potrebbe essere null
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        Thread t1 = pool.acquire(() -> {
        });
    }
}
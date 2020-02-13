import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Es2 {

    public interface Pool<T, R> {
        void add(T x);
        R acquire() throws InterruptedException;
        void release(R x);
    }

    // 2.a
    public interface BasicPool<T> extends Pool<T, T> {
    }

    // 2.b
    public static class SimplePool<T> implements BasicPool<T> {

        private BlockingQueue<T> q = new LinkedBlockingDeque<>();

        @Override
        public void add(T x) {
            q.add(x);
        }

        @Override
        public T acquire() throws InterruptedException {
            return q.take();
        }

        @Override
        public void release(T x) {
            add(x);
        }
    }

    // 2.c + 2.d

    public interface Resource<T> {
        T get();
        void release();
    }

    public static class AutoPool<T> implements Pool<T, Resource<T>> {

        private BlockingQueue<T> q = new LinkedBlockingDeque<>();

        public void add(T x) {
            q.add(x);
        }

        public Resource<T> acquire() throws InterruptedException {
            T r = q.take();
            return new Resource<>() {

                @Override
                public T get() {
                    System.out.println(String.format("acquired: %s", r));
                    return r;
                }

                @Override
                public void release() {
                    System.out.println(String.format("released: %s", r));
                    add(r);
                }

                @SuppressWarnings("deprecation")
                @Override
                public void finalize() {
                    release();
                }
            };
        }

        @Override
        public void release(Resource<T> x) {
            x.release();
        }
    }

    public static void main(String[] args) {
        AutoPool<Integer> pool = new AutoPool<>();
        Random rnd = new Random();
        for (int i = 0; i < 5; ++i) {
            pool.add(i);
        }

        try {
            while (true) {
                Resource<Integer> r = pool.acquire();
                System.out.println("using " + r.get());
                Thread.sleep(Math.abs(rnd.nextInt() % 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

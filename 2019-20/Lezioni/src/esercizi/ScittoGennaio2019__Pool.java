package esercizi;

import java.util.concurrent.LinkedBlockingQueue;

public class ScittoGennaio2019__Pool {

    public interface Pool<T, R> {
        void add(T x); // popola la pool con un nuovo elemento
        R acquire() throws InterruptedException; // acquisisce una risorsa
        void release(R x); // rilascia una risorsa e la rimette nella pool
    }

    // a
    public interface BasicPool<E> extends Pool<E, E> {}

    // b
    public static class SimplePool<T> implements BasicPool<T> {
        private LinkedBlockingQueue<T> q = new LinkedBlockingQueue<>();

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

    // c

    public static class Resource<T> {
        private final T elem;
        private final AutoPool<T> pool;
        public Resource(T x, AutoPool<T> p) {
            this.elem = x;
            this.pool = p;
        }

        public T get() { return elem; }

        @Override
        @SuppressWarnings("deprecation")
        public void finalize() {
            release();
        }

        public void release() {
            pool.release(this);
        }
    }

    public static class AutoPool<T> implements Pool<T, Resource<T>> {


        private LinkedBlockingQueue<T> q = new LinkedBlockingQueue<>();

        @Override
        public void add(T x) {
            q.add(x);
        }

        @Override
        public Resource<T> acquire() throws InterruptedException {
            return new Resource<>(q.take(), this);
        }

        @Override
        public void release(Resource<T> x) {
            q.add(x.elem);
        }

    }

    public static void main(String[] args) {
        AutoPool<String> p = new AutoPool<>();
        try {
            Resource<String> x = p.acquire();

            String s = x.get().toLowerCase();

//            x.release();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ..

    }

}

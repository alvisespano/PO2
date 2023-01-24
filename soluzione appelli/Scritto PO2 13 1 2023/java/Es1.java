import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Es1 {

    public interface Pool<R> {
        R acquire() throws InterruptedException;
        void release(R x);
    }

    // 2.b
    public static class SimplePool<T> implements Pool<T> {

        private final BlockingQueue<T> q = new LinkedBlockingDeque<>();

        @Override
        public T acquire() throws InterruptedException {
            return q.take();
        }

        @Override
        public void release(T x) {
            q.add(x);
        }
    }

    // 2.c + 2.d

    public interface Resource<T> {
        T get();
        void release();
    }

    public static class AutoPool<T> implements Pool<Resource<T>> {

        private final BlockingQueue<T> q = new LinkedBlockingDeque<>();

        public void add(T x) {
            q.add(x);
        }

        public Resource<T> acquire() throws InterruptedException {
            T r = q.take();
            return new Resource<>() {

                @Override
                public T get() {
                    System.out.printf("acquired: %s%n", r);
                    return r;
                }

                @Override
                public void release() {
                    System.out.printf("released: %s%n", r);
                    add(r);
                }

                @SuppressWarnings("deprecation")
                @Override
                protected void finalize() {
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Lambda {

    @FunctionalInterface
    interface Function<T, R> {
        R apply(T x);
    }

    interface Consumer<T> {
        void accept(T x);
    }

    interface Supplier<T> {
        T get();
    }

    interface Runnable {
        void run();
    }

    public static void delay(int ms, Runnable r) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        r.run();
    }

    public static <T> void iter(Collection<T> c, Consumer<T> p) {
        for (T e : c) {
            p.accept(e);
        }
    }

    public static <A, B> List<B> map(Collection<A> a, Function<A, B> f) {
        List<B> l = new ArrayList<>();
        for (A e : a) {
            B b = f.apply(e);
            l.add(b);
        }
        return l;
    }

    public static <T> Collection<T> filter(Collection<T> c, Function<T, Boolean> f) {
        List<T> l = new ArrayList<>();
        for (T e : c) {
            if (f.apply(e))
                l.add(e);
        }
        return l;
    }

    public static <T> List<T> generate(int n, Supplier<T> f) {
        List<T> l = new ArrayList<>();
        for (; n > 0; n--) {
            T x = f.get();
            l.add(x);
        }
        return l;
    }

    static void f(int x) {
        generate(x, () -> x);
    }

    public static void main(String[] args) {
        {
            Runnable pippo = () -> System.out.println("ciao");

            pippo.run();


            int x = 8 * 34 / 67;

            boolean y = x < 0;

            if (y) {
                x = 8;
            }
            else {
                System.out.println(x);
            }

        }



        {
            delay(1000, new Runnable() {
                @Override
                public void run() {
                    System.out.println("ciao!");
                }
            });
        }
        {
            List<String> u = generate(20, () -> "ciao");

            Random rnd = new Random();
            List<Integer> u2 = generate(20, () -> rnd.nextInt(100));

            int k = rnd.nextInt(100);
            List<Integer> u3 = generate(20, () -> k);
        }
        {
            List<Integer> a = new ArrayList<>();
            a.add(10);
            a.add(-345);
            a.add(33);
            Collection<Integer> u = filter(a, x -> x > 0);
        }

        {
            List<Integer> a = new ArrayList<>();
            a.add(1);
            a.add(2);
            a.add(3);
            iter(a, new Consumer<>() {
                @Override
                public void accept(Integer e) {
                    a.add(e);
                }
            });
        }
        {
            List<String> a = new ArrayList<>();
            a.add("pi");
            a.add("po");
            a.add("pa");
            List<Integer> u = map(a, new Function<>() {
                @Override
                public Integer apply(String e) {
                    return e.length();
                }
            });
        }
        {
            List<Integer> a = new ArrayList<>();
            a.add(1);
            a.add(12);
            a.add(23);
            List<Boolean> u = map(a, new Function<>() {
                @Override
                public Boolean apply(Integer e) {
                    return e % 2 == 0;
                }
            });
        }
        {
            List<Integer> a = new ArrayList<>();
            a.add(1);
            a.add(12);
            a.add(23);
            List<Integer> u = map(a, new Function<>() {
                @Override
                public Integer apply(Integer e) {
                    return (int) Math.sqrt(e);
                }
            });
            List<Integer> u2 = map(a, x -> x + 1);


        }

    }


}

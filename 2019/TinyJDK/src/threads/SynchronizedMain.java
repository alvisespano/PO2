package threads;

public class SynchronizedMain {
    public static final Counter counter = new Counter();

    private static class Counter {
        public static final int MAX = 30;

        private int value = 0;

        public synchronized int get() {
            return value;
        }

        public synchronized void set(int x) {
            value = x;
        }

        // questo metodo fa la lettura e il post-incremento in maniera ATOMICA
        public synchronized int getAndIncrement() {
            return value++;
        }
    }

    private static void printAndSleep(int counter, int delay) {
        System.out.println(String.format("thread[%d]: #%d", Thread.currentThread().getId(), counter));
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // usando i metodi get() e set() di Counter, non è garantita l'atomicità dell'operazione di incremento
    // non c'è corruzione di memoria perché sia le letture (get) sia le scritture (set) sono atomiche
    // tuttavia il comportamento non è quello atteso e produce in output più thread che contemporaneamente stampano lo stesso counter
    private static void loop__BAD(int delay) {
        while (counter.get() < Counter.MAX) {
            printAndSleep(counter.get(), delay);
            counter.set(counter.get() + 1);
        }
    }

    // qui invece usiamo un metodo apposito che legge e incrementa in modo ATOMICO
    // in questo modo il comportamento è corretto
    private static void loop__GOOD(int delay) {
        int i;  // occorre una variabile di appoggio: è un binding nello scope locale di una copia dell'intero post-incrementato
        while ((i = counter.getAndIncrement()) < Counter.MAX) {
            printAndSleep(i, delay);    // se al posto della variabile di appoggio i usassimo direttamente counter.get() qui, potremmo ottenere un valore sbagliato        }
        }
    }

    private static void loop(int delay) {
        // cambiare la chiamata per provare entrambe le versioni
        //loop__BAD(delay);
        loop__GOOD(delay);
    }

    private static class MyThread extends Thread {
        private final int delay;

        public MyThread(int delay) {
            this.delay = delay;
        }

        @Override
        public void run() {
            SynchronizedMain.loop(delay);
        }

    }

    public static void main(String[] args) {
        MyThread th = new MyThread(500);
        th.start();
        loop(300);
    }

}

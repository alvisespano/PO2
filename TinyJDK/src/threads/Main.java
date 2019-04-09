package threads;

public class Main {
    public static Integer i = 0;

    private static void loop(int n, int ms) {

        synchronized (i) { i = 0; }
        while (i < n) {
            System.out.println(String.format("thread[%d]: #%d", Thread.currentThread().getId(), i));
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (i) { ++i; }
        }

    }

    public static class MyThread extends Thread {
        private final int n;

        public MyThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            Main.loop(n, 300);
        }

    }


    public static void main(String[] args) {
        MyThread th = new MyThread(23);
        th.start();
        loop(11, 500);
    }

}

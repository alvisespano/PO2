package threads;

public class Main {

    private static void loop(int n, int ms) {
        for (int i = 0; i < n; ++i) {
            System.out.println(String.format("thread[%d]: #%d", Thread.currentThread().getId(), i));
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        th.run();
        loop(11, 500);
    }

}

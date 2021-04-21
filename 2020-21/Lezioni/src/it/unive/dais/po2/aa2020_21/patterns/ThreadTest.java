package it.unive.dais.po2.aa2020_21.patterns;

public class ThreadTest {

    public static void loop() {
        for (int i = 0; i < 10; ++i) {
            System.out.printf("thread#%d: %d\n", Thread.currentThread().getId(), i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyThread extends Thread {

        public MyThread() { super(); }

        @Override
        public void run() {
            loop();
        }

    }

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        t1.start();
        t2.start();

        new Thread(() -> { loop(); }).start();

        loop();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

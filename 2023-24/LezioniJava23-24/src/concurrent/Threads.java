package concurrent;

public class Threads {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("ciao");
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();

        while (true) {
            System.out.println("pippo");
        }

    }

}

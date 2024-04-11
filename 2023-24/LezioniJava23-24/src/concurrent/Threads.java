package concurrent;

public class Threads {
    public static String suffix = "baudo";
    public static void loop(String msg) {
        while (true) {
            System.out.println(msg + " " + suffix);
        }
    }
    public static class MyThread extends Thread {
        @Override
        public void run() {
            loop("ciao");
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();
        loop("pippo");

        // modo alternativo
        Thread t2 = new Thread(() -> loop("ciccio"));
        t2.start();
    }

}

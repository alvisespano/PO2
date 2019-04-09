package patterns.singleton;

public class Main {

    public static void main(String[] args) {
        Singleton single1 = Singleton.getInstance();
        Singleton single2 = Singleton.getInstance();
        System.out.println("is it the same object? " + (single1 == single2));
    }

}

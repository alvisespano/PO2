package patterns.singleton;

public class Main {

    public static void main(String[] args) {
        RandomSingleton o1 = RandomSingleton.getInstance();
        RandomSingleton o2 = RandomSingleton.getInstance();
        System.out.println("are these the same object? " + (o1 == o2 ? "yes" : "no"));
    }

}

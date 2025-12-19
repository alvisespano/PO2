package tinyjdk.dais.unive.it;



public class Test {
    public static void main(String[] args) {
    }

    public static void foo(Collection<Integer> c) {
        c.add(1);
        c.add(62);
        c.add(234);

        Iterator<Integer> it = c.iterator();
        while (it.hasNext()) {
            Integer e = it.next();
            System.out.println(e);
        }

    }
}

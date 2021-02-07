package it.unive.dais.po2.myjdk;

public class TestList {
    public static void main(String[] args) {
        try {
            MyList<Integer> a = new MyArrayList<>();
            a.add(23);
            a.add(11);

            int n = a.get(1);
            System.out.println("elemento in posizione 1: " + n);

        } catch (OutOfBoundsException e) {
            System.out.println("eccezione: " + e.getMessage());
        }

    }
}

package it.unive.dais.po2.myjdk;

public class TestList {

    public static void main(String[] args) {
        try {
            /*
            * Si sussume fino al minimo dei metodi che servono
            */
            // subsunction di MyArrayList<Integer> a = new MyArrayList<>();
            MyList<Integer> a = new MyArrayList<>();
            a.add(23); // add ereditato da MyCollection
            a.add(11);

            int n = a.get(1);
            System.out.println("Elemento in posizione 1: " + n);

        } catch (OutOfBoundsException e) {
            System.out.println("Eccezione: " + e.getMessage());
        }

    }
}

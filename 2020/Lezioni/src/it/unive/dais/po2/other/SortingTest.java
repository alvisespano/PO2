package it.unive.dais.po2.other;

import it.unive.dais.po2.zoo.Dog;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortingTest {


    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Metodo che genera stringhe alfanumeriche random.
     */
    @NotNull
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l.add(rand.nextInt(100));
        }
        List<String> l2 = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l2.add(randomAlphaNumeric(10));
        }

        List<Dog> l3 = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l3.add(new Dog(rand.nextInt(100), randomAlphaNumeric(5)));
        }

        // Lista di numeri
        System.out.println(l);
        Collections.sort(l);
        System.out.println(l);

        // Lista di stringhe in ordine lessicografico
        System.out.println(l2);
        Collections.sort(l2);
        System.out.println(l2);

        // Lista di cani
        System.out.println(l3);
        Collections.sort(l3);
        sort__(l3);
        System.out.println(l3);

    }

    /**
     * Metodo sort del JDK
     */
    static <T extends Comparable<? super T>> void sort__(List<T> list) {}

}


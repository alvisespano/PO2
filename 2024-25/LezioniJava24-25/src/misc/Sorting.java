package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {


    public static <T extends Comparable<? super T>> void sort(List<T> list) { ... }


    /*public interface Comparator<T> {
        int compare(T o1, T o2);
    }*/

    public static void main(String[] args) {
        {
            List<Zoo.Dog> l = new ArrayList<>();
            l.add(new Zoo.Dog(20, "mio"));
            l.add(new Zoo.Dog(29, "mio"));
            l.add(new Zoo.Dog(28, "mio"));
            sort(l);




            Collections.sort(l, new Comparator<>() {
                @Override
                public int compare(Zoo.Animal a, Zoo.Animal b) {
                    return a.weight - b.weight;
                }
            });
        }
        {
            List<String> l = List.of("ciao", "pippo", "gigio", "byebye");
            Collections.sort(l, new Comparator<>() {
                public int compare(String a, String b) {
                    return b.length() - a.length();
                }
            });
        }
    }


}

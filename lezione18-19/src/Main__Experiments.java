import zoo.Animale;
import zoo.Cane;
import zoo.Gatto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// esperimenti col type system di Java
public class Main__Experiments {


    public static void main__wildcards() {

        // inizializzazioni
        List<?> l1 = new ArrayList<Cane>();
        Object o = l1.get(0);
        List<? extends Animale> l2 = new ArrayList<Gatto>();
        List<Animale> l3 = new ArrayList<Cane>();   // questo non è accettato dal compilatore
        List<? super Animale> l4 = new ArrayList<Animale>();

        // assegnamenti
        l1 = l2;    // questo si può fare
        l2 = l1;    // questo no

        Object a = l4.get(0);

    }


}

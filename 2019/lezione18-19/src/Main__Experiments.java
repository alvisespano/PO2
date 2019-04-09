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
        Object x = l1.get(0);


        List<? extends Animale> l2 = new ArrayList<Gatto>();
        Animale a = l2.get(0);
//        l2.add(new Animale());

        List<Animale> l3 = new ArrayList<Gatto>();   // questo non è accettato dal compilatore


        List<? super Animale> l4 = new ArrayList<Animale>();

        // assegnamenti
        l1 = l2;    // questo si può fare
        l2 = l1;    // questo no

        Object a = l4.get(0);

    }


    public static class C {

        public int m() { return 1; }
        public int m(int x) { return x + 1; }
        public int m(float x) { return (int) (x - 1.0f); }
        public int m(Number x) { return 3; }

        public <T> int m(T x) { return 1; }

        public void p(Animale a) {}
        public void p(Cane c) {}
        public <T extends Cane> void p(T t) {} // questo overload non si può fare7

        public void p(Cane a, Cane b) {}
        public void p(Animale a, Cane b) {}
        public void p(Cane a, Animale b) {}
        public void p(Animale a, Animale b) {}
    }

    public static void main_overload() {
        C c = new C();
        int a =c.m(4.6);

        Cane fido = new Cane();
        Animale fido2 = fido;
        c.p(fido);
        c.p(fido2);

        c.p(fido, fido);
    }





}

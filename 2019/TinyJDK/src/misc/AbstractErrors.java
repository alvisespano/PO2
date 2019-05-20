package misc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AbstractErrors {

    public static abstract class A {

        public A() {
            System.out.println("Constructing A");
            virtual();
        }

        public abstract void virtual();
    }

    public static class B extends A {
        public B() {
            System.out.println("Constructing B");
        }

        @Override
        public void virtual() {
            System.out.println("Using B");
        }
    }


    public static void main(String[] args) {
        callToAbstract();
    }

    public static void constructorOrder() {
        new B();
    }

    public static void callToAbstract() {
        try {
            Class<A> cl = A.class;
            Constructor<A> cons = cl.getConstructor(new Class[] {});
            A a = cons.newInstance();   // se si può costruire, l'invocazione appena sotto sarà ad un metodo astratto!
            a.virtual();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

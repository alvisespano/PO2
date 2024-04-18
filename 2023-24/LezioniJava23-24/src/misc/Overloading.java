package misc;

import java.util.List;

public class Overloading {

    public static class A {
        public Number m() { return 1.3; }
    }

    public static class B extends A {
        @Override
        public Integer m() { return 2; }
    }


    public static void main(String[] args) {
        A a = new B();
        Number u = a.m();
    }

}

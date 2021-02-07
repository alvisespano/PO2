package esercizi;

public class DynamicDispatch {

    public static class A {

        public void m() {
            System.out.println("A::m");
        }

        public void p(int n) {}
        public void p(double n) {}

    }
    /*
       A
       SLOT1: m         --> A::m
       SLOT2: p(int)    --> A::p(int)
       SLOT3: p(double) --> A::p(double)

       B
       SUPER: A
       SLOT1: m          --> B::m
       SLOT2: p(int)     --> A::p(int)
       SLOT3: p(double)  --> A::p(double)
       SLOT4: p(String)  --> B::p(String)
       SLOT5: q(String)  --> B::q(String)
     */

    public static class B extends A {

        @Override
        public void m() {
            System.out.println("B::m");
        }

        public void p(String n) {}
        public void q(String n) {}
    }

    public static class C {

        public void a() {}
        public void p(String n) {}
        public void q(String n) {}
        public void m() {
            System.out.println("C::m");
        }
    }

    /*
       D
       SUPER: B
       SLOT1: m          --> B::m
       SLOT2: p(int)     --> A::p(int)
       SLOT3: p(double)  --> A::p(double)
       SLOT4: p(String)  --> B::p(String)
       SLOT5: q(String)  --> D::q(String)
     */
    public static class D extends B {
        @Override
        public void q(String s) {}
    }

    public static void main(String[] args) {
        A a = new B();
        a.m();

        B b = new D();
        b.q("ciao");
    }

}

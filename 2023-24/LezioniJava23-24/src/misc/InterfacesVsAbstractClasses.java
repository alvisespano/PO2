package misc;

public class InterfacesVsAbstractClasses {

    public interface I {
        void a();
        void b();
        default void c() {
            a();
            b();
        }
    }

    public static abstract class J {
        public abstract void a();
        public abstract void b();
        public void c() {
            a();
            b();
        }
    }




    public static class C implements I {

        @Override
        public void a() {

        }

        @Override
        public void b() {

        }
    }

    public static void main(String[] args) {
        I o = new C();
    }
}

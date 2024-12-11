package tinyjdk;


public class Pair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() { return a;}
    public B getB() { return b;}
    public void setA(A a) { this.a = a;}
    public void setB(B b) { this.b = b;}



    public static void main(String[] args) {
        Pair<Boolean, String> p = new Pair<>(true, "ciao");
        p.a = false;
        if (p.a) {

        }
        else {

        }
    }

}

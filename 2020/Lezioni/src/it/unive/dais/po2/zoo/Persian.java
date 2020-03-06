package it.unive.dais.po2.zoo;

public class Persian extends Cat {
    public Persian(int w, Cat p) {
        super(w, "beige", p);
    }

    @Override
    public void meow() {
        System.out.println("maoo!");
    }
}

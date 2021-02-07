package it.unive.dais.po2.zoo;

public class Persian extends Cat {
    public Persian(int w) {
        super(w, "beige");
    }

    @Override
    public void meow() {
        System.out.println("maoo!");
    }
}

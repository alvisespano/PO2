package it.unive.dais.po.tinylib;

public class OverloadTest {

    // definiamo la gerarchia dello zoo velocemente tramite classi nested statiche
    public static class EssereVivente {}
    public static class Animale extends EssereVivente {}
    public static class Pianta extends EssereVivente {}
    public static class Cane extends Animale {}
    public static class Gatto extends Animale {}

    public <T> T m(T x) { return x; }

    public EssereVivente m(EssereVivente x) { return x; }

    public EssereVivente m(Animale x) { return x; }

    public EssereVivente m(Cane x) { return x; }


    public static void prova() {
        OverloadTest t = new OverloadTest();

        Animale a = new Cane();
        t.m(t.m(a));

    }
}

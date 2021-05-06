package it.unive.dais.po2.aa2020_21.others;

public class SingletonMain {

    public static void main(String[] args) {
        try {
            Singleton s1 = Singleton.getInstance(7);
            Singleton s2 = Singleton.getInstance(8);
            Singleton s3 = Singleton.getInstance(9);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package it.unive.dais.po.pitagora;

public class Main {

    private static class Lato {
        private double x;
        public void set(double x) { this.x = x; }
        public double get() { return x; }
    }

    public static void main(String[] args) {
        Double catetoMinore = -1., catetoMaggiore = -1., ipotenusa = -1.;

        try {
            for (int i = 0; i < args.length; ++i) {
                System.out.println(s + "\n");

                checkArg(i, args, "-c", catetoMinore);
                checkArg(i, args, "-C", catetoMaggiore);
                checkArg(i, args, "-i", ipotenusa);
            }

        } catch (Exception e) {
            System.out.println("exception caught: " + e.getMessage());
        }

    }

    private static void checkArg(int i, String[] args, String option, Double val) {
        if (args[i].equals(option)) {
            if (val > -1) throw new IllegalArgumentException(String.format("argument has already been passed as %g", val));
            val = Double.parseDouble(args[i + 1]);
            if (val <= 0.) throw new IllegalArgumentException(String.format("argument %g is negative", val));
        }

    }
}

package it.unive.dais.po.pitagora;

public class Main {


    private static class Lato {
        private double x = -1.;

        public void set(double x) {
            if (x <= 0.) throw new IllegalArgumentException(String.format("argument %g is negative", x));
            this.x = x;
        }

        public double get() {
            if (x <= -1) throw new RuntimeException("uninitialized value");
            return x;
        }

        public boolean isInitialized() {
            return x > -1;
        }
    }

    public static void main(String[] args) {
        Lato catetoMinore = new Lato(), catetoMaggiore = new Lato(), ipotenusa = new Lato();

        try {
            for (int i = 0; i < args.length; ++i) {
                System.out.println(String.format("args[%d] = \"%s\"", i, args[i]));
                checkArg(i, args, "-c", catetoMinore);
                checkArg(i, args, "-C", catetoMaggiore);
                checkArg(i, args, "-i", ipotenusa);
            }
        } catch (Exception e) {
            System.out.println("exception caught: " + e.getMessage());
        }

    }

    private static void checkArg(int i, String[] args, String option, Lato l) {
        if (args[i].equals(option)) {
            if (l.isInitialized())
                throw new IllegalArgumentException(String.format("argument has already been passed as %g", l));
            l.set(Double.parseDouble(args[i + 1]));
        }
    }
}

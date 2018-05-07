package it.unive.dais.po.pitagora;

public class Main {


    public static void main(String[] args) {
        Lato catetoMinore = new Lato(), catetoMaggiore = new Lato(), ipotenusa = new Lato();

        try {
            for (int i = 0; i < args.length; ++i) {
                System.out.println(String.format("args[%d] = \"%s\"", i, args[i]));
                checkArg(i, args, "-c", catetoMinore);
                checkArg(i, args, "-C", catetoMaggiore);
                checkArg(i, args, "-i", ipotenusa);
            }

            if (catetoMinore.isInitialized() && catetoMaggiore.isInitialized() && ipotenusa.isInitialized()) {
                // TODO
            }

            else if (catetoMinore.isInitialized() && catetoMaggiore.isInitialized()) {
                ipotenusa.set(Math.sqrt(Math.pow(catetoMaggiore.get(), 2.0) + Math.pow(catetoMinore.get(), 2.0)));
            }

            else if (ipotenusa.isInitialized() && catetoMaggiore.isInitialized()) {
                catetoMinore.set(Math.sqrt(Math.pow(ipotenusa.get(), 2.0) - Math.pow(catetoMaggiore.get(), 2.0)));
            }

            else if (ipotenusa.isInitialized() && catetoMinore.isInitialized()) {
                catetoMaggiore.set(Math.sqrt(Math.pow(ipotenusa.get(), 2.0) - Math.pow(catetoMinore.get(), 2.0)));
            }

            System.out.println("cateto minore = " + catetoMinore);
            System.out.println("cateto maggiore = " + catetoMaggiore);
            System.out.println("ipotenusa = " + ipotenusa);


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

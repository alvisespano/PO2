package it.unive.dais.po.pitagora;

public class Main {

    public static double square(double x) {
        return x * x;
    }

    public static boolean areInitialized(Lato a, Lato b) {
        return a.isInitialized() && b.isInitialized();
    }


    public static void main(String[] args) {
        // TODO: creare un tipo che implementa Numeric
        Lato catMin = new Lato(), catMag = new Lato(), ipo = new Lato();

        try {
            for (int i = 0; i < args.length; ++i) {
                System.out.println(String.format("args[%d] = \"%s\"", i, args[i]));
                checkArg(i, args, "-c", catMin);
                checkArg(i, args, "-C", catMag);
                checkArg(i, args, "-i", ipo);
            }

            if (catMin.isInitialized() && catMag.isInitialized() && ipo.isInitialized()) {
                System.out.println("verifico i 3 lati");
                if (Math.pow(ipo.get(), 2.0) == Math.pow(catMag.get(), 2.0) + Math.pow(catMin.get(), 2.0))
                    System.out.println("E' veramente un triangolo rettangolo");
                else
                    System.out.println("Non è un triangolo rettangolo");
            }

            else if (areInitialized(catMin, catMag)) {
                System.out.println("calcolo l'ipotenusa");
                ipo.set(Math.sqrt(square(catMag.get()) + square(catMin.get())));
            }

            else if (ipo.isInitialized() && catMag.isInitialized()) {
                System.out.println("calcolo il cateto minore");
                catMin.set(Math.sqrt(square(ipo.get()) - catMag.square()));
            }

            else if (ipo.isInitialized() && catMin.isInitialized()) {
                System.out.println("calcolo il cateto maggiore");
                catMag.set(Math.sqrt(Math.pow(ipo.get(), 2.0) - Math.pow(catMin.get(), 2.0)));
            }

            else {
                throw new IllegalArgumentException("devi specificare almeno 2 lati del triangolo rettangolo");
            }

            System.out.println("cateto minore = " + catMin);
            System.out.println("cateto maggiore = " + catMag);
            System.out.println("ipotenusa = " + ipo);


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

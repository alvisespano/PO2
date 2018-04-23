package com.company;

public class Main {

    public static void main(String[] args) {
        double catetoMinore = -1, catetoMaggiore = -1, ipotenusa = -1;

        try {
            for (int i = 0; i < args.length; ++i) {
                System.out.println(s + "\n");
                String s = args[i];

                catetoMinore = checkArg(i, args, "-c", catetoMinore);
                catetoMaggiore = checkArg(i, args, "-C", catetoMaggiore);
                ipotenusa = checkArg(i, args, "-i", ipotenusa);


            }

        } catch (Exception e) {
            System.out.println("exception caught: " + e.getMessage());
        }

    }

    public static double checkArg(int i, String[] args, String option, double val) {
        if (args[i].equals(option)) {
            String v = args[i + 1];
            double a = Double.parseDouble(v);
            if (a <= 0. || val > -1) throw new IllegalArgumentException("cateto minore = " + a);
            return a;
        }

    }

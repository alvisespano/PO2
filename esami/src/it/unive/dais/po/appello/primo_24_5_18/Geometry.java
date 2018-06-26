package it.unive.dais.po.appello.primo_24_5_18;

import java.util.List;

public class Geometry {

    // classe punto immutabile
    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static abstract class Line<S extends Line<S>> implements Comparable<S> {
        public abstract double length();

        @Override
        public int compareTo(S l) {
            return Double.compare(length(), l.length());
        }
    }

    public static class Segment extends Line<Segment> {
        private Point p1, p2;

        public Segment(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        // implementazione della length() per i Segment
        @Override
        public double length() {
            return Math.sqrt(Math.pow(p1.x - p2.x, 2.) + Math.pow(p1.y - p2.y, 2.));
        }
    }

    public static class Polyline extends Line<Polyline> {
        private List<? extends Point> points;

        public Polyline(List<? extends Point> points) {
            this.points = points;
        }

        // implementazione della length() per le Polyline tramite la length() dei Segment
        @Override
        public double length() {
            double r = 0.;
            for (int i = 0; i < points.size() - 1; ++i)
                r += new Segment(points.get(i), points.get(i + 1)).length();
            return r;
        }
    }

    // sottoclasse Punto3D con solo il campo z (immutabile anch'esso) in più
    private static class Point3D extends Point {
        private final double z;
        public Point3D(double x, double y, double z) {
            super(x, y);
            this.z = z;
        }
    }

    public static void main(String[] args) {
        Point a = new Point3D(0., 0., 0.), b = new Point3D(2., 2., 2.), c = new Point3D(2., 0., 3.);
        Polyline abc = new Polyline(List.of(a, b, c)), acb = new Polyline(List.of(a, c, b));
        System.out.println(String.format("max length = %g", max(abc, acb).length()));
    }

    // impl
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }


}

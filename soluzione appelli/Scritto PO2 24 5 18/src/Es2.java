
import java.util.List;

public class Es2 {

    public class Geometry { // questa enclosing class non è necessaria, essendoci già la classe Es2, tuttavia il testo è dato così

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

            // 2.a
            @Override
            public double length() {
                // implementazione della length() per i Segment
                return Math.sqrt(Math.pow(p1.x - p2.x, 2.) + Math.pow(p1.y - p2.y, 2.));
            }
        }

        public static class Polyline extends Line<Polyline> {
            private List<? extends Point> points;

            public Polyline(List<? extends Point> points) {
                this.points = points;
            }

            // 2.b
            @Override
            public double length() {
                // implementazione della length() per le Polyline tramite la length() dei Segment
                double r = 0.;
                for (int i = 0; i < points.size() - 1; ++i)
                    r += new Segment(points.get(i), points.get(i + 1)).length();
                return r;
            }
        }
    }

    // 2.c.i
    private static class Point3D extends Point {
        private final double z;

        public Point3D(double x, double y, double z) {
            super(x, y);
            this.z = z;
        }
    }

    public static void main(String[] args) {
        Point a = new Point3D(0., 0., 0.),
              b = new Point3D(2., 2., 2.),
              c = new Point3D(2., 0., 3.);
        Polyline abc = new Polyline(List.of(a, b, c)), acb = new Polyline(List.of(a, c, b));
        System.out.println(String.format("max length = %g", max(abc, acb).length()));
    }

    // 2.c.ii
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    // 2.c.iii
    // RISPOSTA:
    //  TEMA A: √2 + 2
    //  TEMA B: 2√2 + 2


}

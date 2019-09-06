import java.util.*;

public class Es {

    // 1.a
    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // 1.b
    public static class Line {
        private final Point p1, p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double length() {
            return Math.sqrt(Math.pow(p1.x - p2.x, 2.) + Math.pow(p1.y - p2.y, 2.));
        }
    }

    // 1.c
    public abstract static class Polygon {
        protected final List<Point> points;

        protected Polygon(List<Point> points) {
            assert points.size() >= 3;  // 1.c.i: RISPOSTA: il motivo per cui è necessario questo assert è che non è possibile vincolare la lunghezza di una lista con il type system: i tipi non esprimono valori numerici statici
            this.points = points;
        }

        // 1.c.ii
        public Iterator<Line> lineIterator() {
            Iterator<Point> it = points.iterator();
            final Point[] last = new Point[1];  // questo trucco serve a mettere il final sull'array e non sugli elementi, così da poter essere modificato da dentro la anonymous class
            assert it.hasNext();                // si assicura che non iteriamo su una lista vuota (in realtà ha almeno 3 elementi)
            last[0] = it.next();
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public Line next() {
                    Point p = it.next();
                    Line r = new Line(last[0], p);
                    last[0] = p;
                    return r;
                }
            };
        }

        // 1.c.iii
        public double perimeter() {
            Iterator<Line> lineIt = lineIterator();
            double r = 0.;
            while (lineIt.hasNext()) {
                r += lineIt.next().length();
            }
            return r;
        }

        public abstract double area();
    }

    // 2.a
    public static class Triangle extends Polygon {
        public Triangle(Point p1, Point p2, Point p3) {
            super(List.of(p1, p2, p3));
        }

        @Override
        public double area() {
            Point p1 = points.get(0), p2 = points.get(1);
            // versione facile con la base orizzontale (sufficiente per la valutazione dell'esame)
            if (p1.x == p2.x) {
                double base = new Line(p1, p2).length(), h = new Line(new Point(p2.x, p1.y), p2).length();
                return base * h / 2.;
            }
            // versione generale con la formula di Erone (non necessaria per la valutazione dell'esame)
            else {
                Point p3 = points.get(2);
                double p = perimeter() / 2.,    // semiperimetro
                        a = new Line(p1, p2).length(),
                        b = new Line(p2, p3).length(),
                        c = new Line(p3, p1).length();
                return Math.sqrt(p * (p - a) * (p - b) * (p - c));
            }
        }

    }

    // 2.b
    public static class RightTriangle extends Triangle {
        public RightTriangle(Point p1, double b, double h) {
            super(p1, new Point(p1.x + b, p1.y), new Point(p1.x, p1.y + h));
        }
    }

    // 2.c
    public static class Rectangle extends Polygon {
        public Rectangle(Point p1, Point p3) {
            super(List.of(p1, new Point(p1.x, p3.y), p3, new Point(p3.x, p1.y)));
        }

        @Override
        public double area() {
            Point p1 = points.get(0), p2 = points.get(1), p4 = points.get(3);
            Line b = new Line(p1, p4), h = new Line(p1, p2);
            return b.length() * h.length();
        }
    }

    // 2.d
    public static class Square extends Rectangle {
        public Square(Point p1, double side) {
            super(p1, new Point(p1.x + side, p1.y + side));
        }

    }

    // 3.a
    static <T> T max(Collection<T> l, Comparator<? super T> cmp) {
        assert l.size() > 1;
        T max = l.iterator().next();    // prendo il primo elemento
        for (T x : l) {
            if (cmp.compare(x, max) > 0) max = x;
        }
        return max;
    }

    public static void main(String[] args) {
        Square sq1 = new Square(new Point(10., -4.), 0.1),
                sq2 = new Square(new Point(1., 20.), 0.01);
        Collection<Square> squares = List.of(sq1, sq2);
        Rectangle r = max(squares, new Comparator<Polygon>() {
            @Override
            public int compare(Polygon a, Polygon b) {
                return (int) (a.area() - b.area());
            }
        });
        // 3.b: il metodo max() ritorna sq2 perché la sottrazione nel confronto è invertita, quindi la ricerca del massimo in realtà restituisce il più piccolo anziché il più grande;
        //      ed il quadrato con area minore è sq2, perché 0.01^2 = 0.0001 mentre 0.1^2 = 0.01, quindi sq2 è di fatto il più piccolo.

        // 3.c: l'area di r (cioè di sq2) è 0.01^2 = 0.0001 = 10^-4
    }

}

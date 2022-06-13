import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 3/6/2022 per ciò che riguarda i quesiti 1-5, ovvero le domande che coinvolgono Java.
// Il quesito 6 riguardante C++ è in una Solution per Visual Studio a parte, non qui.

public class Appello_3_6_22 {

    public static <T, State> State fold(Iterable<T> i, final State st0, BiFunction<State, T, State> f) {
        State st = st0;
        for (final T e : i) {
            st = f.apply(st, e);
        }
        return st;
    }

    // 1.a
    public static <T> double sumBy(Iterable<T> i, Function<T, Double> f) {
        return fold(i, 0., (r, x) -> r + f.apply(x));
    }

    // 1.b
    public static <T> int compareBy(T s1, T s2, Function<T, Double> f) {
        return Double.compare(f.apply(s1), f.apply(s2));
    }

    public static class Edge implements Comparable<Edge> {
        private final double len;

        public Edge(double len) {
            this.len = len;
        }

        public double length() {
            return len;
        }

        // 2.a
        @Override
        public int compareTo(Edge s) {
            return compareBy(this, s, Edge::length);
        }
    }

    public interface Surface extends Comparable<Surface> {
        double area();

        double perimiter();

        // 2.b
        @Override
        default int compareTo(Surface s) {
            return compareBy(this, s, Surface::area);
        }
    }

    public interface Polygon extends Surface, Iterable<Edge> {
        // 2.c
        @Override
        default double perimiter() {
            return sumBy(this, Edge::length);
        }
    }

    public interface Solid extends Comparable<Solid> {
        double outerArea();

        double volume();

        // 2.d
        default int compareTo(Solid s) {
            return compareBy(this, s, Solid::volume);
        }
    }

    public interface Polyhedron<P extends Polygon> extends Solid, Iterable<P> {
        // 2.e
        @Override
        default double outerArea() {
            return sumBy(this, P::area);
        }
    }

    // 3.a
    public static class Sphere implements Solid {
        private final double radius;

        public Sphere(double radius) {
            this.radius = radius;
        }

        @Override
        public double outerArea() {
            return 4. * Math.PI * radius * radius;
        }

        @Override
        public double volume() {
            return 4. / 3. * Math.PI * radius * radius * radius;
        }
    }

    // 3.b
    public static class Cilinder implements Solid {
        private final double radius, height;

        public Cilinder(double radius, double height) {
            this.radius = radius;
            this.height = height;
        }

        @Override
        public double outerArea() {
            double b = Math.PI * radius * radius, l = 2. * Math.PI * radius * height;   // hanno capito solo laterale
            return 2. * b + l;
        }

        @Override
        public double volume() {
            return Math.PI * radius * radius * height;
        }
    }

    // 3.c
    public static class Rectangle implements Polygon {
        private final double width, height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public Iterator<Edge> iterator() {
            Edge w = new Edge(width), h = new Edge(height);
            return List.of(w, h, w, h).iterator();
        }
    }

    // 3.d
    public static class Square extends Rectangle {
        public Square(double side) {
            super(side, side);
        }
    }

    public static class Parallelepiped implements Polyhedron<Rectangle> {
        protected double width, height, depth;

        public Parallelepiped(double width, double height, double depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }

        // 4.a
        @Override
        public double volume() {
            return width * height * depth;
        }

        @Override
        public Iterator<Rectangle> iterator() {
            Rectangle r1 = new Rectangle(width, height), r2 = new Rectangle(width, depth), r3 = new Rectangle(height, depth);
            return List.of(r1, r2, r3, r1, r2, r3).iterator();
        }
    }

    // 4.b
    public static class Cube extends Parallelepiped {
        public Cube(double side) {
            super(side, side, side);
        }
    }


    public static void main(String[] args) {
        // 4.d
        {
            int facet_cnt = 1;
            // questo foreach non compila perché Cube è sottotipo di Iterable<Rectangle>, non di Iterable<Square>
            // si badi che NON è possibile co-variare il tipo di ritorno del metodo iterator() di Cube in modo che si specializzi in Iterator<Square>
            // perché è sound co-variare il tipo più esterno di un tipo parametrico, ma non il type argument
            // for (Square sq : new Cube(10.)) {
            for (Rectangle sq : new Cube(10.)) {  // così invece compilerebbe
                int side_cnt = 1;
                for (Edge e : sq) {
                    System.out.printf("side #%d/%d = %f\n", side_cnt++, facet_cnt, e.length());
                }
                ++facet_cnt;
            }
        }

        // 5
        {
            Cube c1 = new Cube(1.), c2 = new Cube(2.);
            Parallelepiped p1 = new Parallelepiped(1., 2., 3.), p2 = new Parallelepiped(2., 3., 4.);
            List<Polyhedron<? extends Rectangle>> polys = new ArrayList<>(List.of(c1, c2, p1, p2));

            // per testare rapidamente i risultati di queste sort, si usi debugger
            Collections.sort(polys);                                                            // c1
            Collections.sort(polys, (x, y) -> compareBy(x, y, Polyhedron::outerArea));          // c1
            Collections.sort(polys, (x, y) -> compareBy(x, y, (p) -> p.outerArea()));           // c1
//            Collections.sort(polys, (x, y) -> compareBy(x, y, (r) -> r.perimiter()));         // non compila
            Collections.sort(polys, (x, y) -> compareBy(x, y, new Function<>() {                // c1
                @Override
                public Double apply(Polyhedron<? extends Rectangle> r) {
                    return r.volume();
                }
            }));
//            Collections.sort(polys, (x, y) -> Double.compare(sumBy(x, Square::perimiter),     // non compila
//                                                             sumBy(y, Rectangle::perimiter)));
        }
    }
}

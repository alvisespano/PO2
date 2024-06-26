import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Es1 {

    // 1.a
    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // 1.b
    public static class Segment {
        public final Point a, b;

        public Segment(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        public double length() {
            return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
        }
    }

    // 1.c
    public static abstract class Polygon implements Iterable<Segment> {
        protected final List<Point> points;

        protected Polygon(List<Point> points) {
            assert points.size() >= 3;
            this.points = points;
        }

        public Iterator<Segment> iterator() {
            return new Iterator<Segment>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < points.size();
                }

                @Override
                public Segment next() {
                    return new Segment(points.get(i++), points.get(i % points.size()));
                }
            };
        }

        public double perimeter() {
            double r = 0.;
            for (Segment s : this)
                r += s.length();
            return r;
        }

        public abstract double area();
    }

    // 1.d.i
    public static class Triangle extends Polygon {
        public Triangle(Point p1, Point p2, Point p3) {
            super(List.of(p1, p2, p3));
        }

        @Override
        public double area() {
            double p = perimeter() / 2.;
            List<Double> segs = new ArrayList<>();
            for (Segment s : this) segs.add(s.length());
            return Math.sqrt(p * (p - segs.get(0)) * (p - segs.get(1)) * (p - segs.get(2)));
        }
    }

    // 1.d.ii
    public static class Rectangle extends Polygon {
        public Rectangle(Point p1, Point p3) {
            super(List.of(p1, new Point(p3.x, p1.y), p3, new Point(p1.x, p3.y)));
        }

        @Override
        public double area() {
            Iterator<Segment> it = iterator();
            double b = it.next().length(), h = it.next().length();
            return b * h;
        }
    }

    // 1.d.iii
    public static class Square extends Rectangle {
        public Square(Point p1, double side) {
            super(p1, new Point(p1.x + side, p1.y + side));
        }

        // 1.d.iv
        // In generale non vale la pena fare questo override.
        // Sicuramente non è necessario perché il metodo area() ereditato da Rectangle è più che sufficiente.
        // Volendo si può fare un ragionamento sulla performance: calcolare il lato e poi calcolarne il quadrato è
        // più performante rispetto a pescare i primi due lati dall'iteratore di Segment e moltiplicarne le lunghezze,
        // però parliamo di un vantaggio davvero negligibile.
        @Override
        public double area() {
            // Il costruttore potrebbe salvare side in un campo ed evitare la seguente sottrazione
            // ma sarebbe una replicazione di dati non molto elegante.
            double side = points.get(1).x - points.get(0).x;
            return side * side;
        }
    }

    // 1.e.i
    public static void main(String args[]) {
        Square sq1 = new Square(new Point(11.235, -8.53), 0.1),
                sq2 = new Square(new Point(1., 20.), 0.01),
                sq3 = new Square(new Point(0., 0.), 0.2);
        List<Square> squares = new ArrayList<>();   // nel testo d'esame qui c'era List.of(sq1, sq2, sq3) per brevità
        squares.add(sq1);                           // ma List.of() produce liste immutabili, che non possono essere ordinate
        squares.add(sq2);                           // quindi qui abbiamo fatto un arraylist popolato a mano
        squares.add(sq3);
        Square r = min(squares, (a, b) -> (int) (a.area() - b.area()));
    }

    static <T> T min(List<T> l, Comparator<? super T> c) {
        Collections.sort(l, c);
        return l.get(0);
    }

    // 1.e.ii
    // sq1 == r
    // ATTENZIONE: il quadrato con l'area più piccola teoricamente è sq2; tuttavia a causa del cast a int nella lambda
    // del Comparator tutte le differenze tra le aree vengono arrotondate a 0. L'algoritmo di sorting è stabile sull'ordine originale
    // degli elementi, quindi alla fine viene fuori sq1 semplicemente perché è il primo della lista.
    // Le altre opzioni possibili nel testo dell'esame sono tutte false; in particolar modo quelle che usano equals(),
    // perché non c'è nessun override di equals() per la classe Square, quindi viene invocata la versione ereditata
    // da Object che restituisce false di default.
}

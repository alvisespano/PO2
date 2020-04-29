package esercizi;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;

public class ScrittoGennaio2020 {

    public static class Point {

    }

    public interface Solid extends Comparable<Solid> {
        double area();
        double volume();
        PositionedSolid at(Point origin);

        static <S extends Solid> int compareBy(Function<S, Double> f, S s1, S s2) {
            return Double.compare(f.apply(s1), f.apply(s2));
        }
        static <S extends Solid> Comparator<S> comparatorBy(Function<S, Double> f) {
            return (s1, s2) -> compareBy(f, s1, s2);
        }
        default int compareTo(Solid s) {
            Function<Solid, Double> f = Solid::volume; // (Solid x) -> x.volume()
            Supplier<Double> g = this::volume;         // () -> this.volume()
            return compareBy(Solid::volume, this, s);
        }
    }

    public interface Polyhedron extends Solid {
        double perimeter();
        @Override
        PositionedPolyhedron at(Point origin);
    }

    public interface PositionedSolid {
        Point origin();
    }

    public interface PositionedPolyhedron extends PositionedSolid, Iterable<Point> {}

}

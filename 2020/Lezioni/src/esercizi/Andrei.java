package esercizi;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

public class Andrei {

    public static class Point {
        public final double x, y, z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point move(double dx, double dy, double dz) {
            return new Point(this.x + dx, this.y + dy, this.z + dz);
        }

        @Override
        public String toString() {
            return String.format("(%g, %g, %g)", x, y, z);
        }
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

    public interface PositionedPolyhedron extends PositionedSolid, Iterable<Point> {
    }

    public static class Cube implements Polyhedron {
        private double side;

        public Cube(double side) {
            this.side = side;
        }

        @Override
        public double area() {
            return 4 * Math.pow(side, 2);
        }

        @Override
        public double volume() {
            return Math.pow(side, 3);
        }

        @Override
        public double perimeter() {
            return side * 12;
        }

        @Override
        public PositionedPolyhedron at(Point origin) {
            return new PositionedPolyhedron() {
                @Override
                public Point origin() {
                    return origin;
                }

                @NotNull
                @Override
                public Iterator<Point> iterator() {
                    Point[] vertici = {origin, origin.move(side, 0, 0), origin.move(0, side, 0), origin.move(0, side, 0), origin.move(side, side, 0), origin.move(side, 0, side)/*ecc*/};
                    return Arrays.asList(vertici).iterator();
                }
            };
        }

    }

    static class Sphere implements Solid {
        private double ray;

        public Sphere(double ray) {
            this.ray = ray;
        }

        @Override
        public double area() {
            return 4 * 3.14 * Math.pow(ray, 2);
        }

        @Override
        public double volume() {
            return (4. / 3.) * 3.14 * Math.pow(ray, 3);
        }

        @Override
        public PositionedSolid at(Point origin) {
            return new PositionedSolid() {
                @Override
                public Point origin() {
                    return origin;
                }
            };
        }
    }

    public static void main(String[] args) {

        Cube cube1 = new Cube(11.), cube2 = new Cube(23.);
        Sphere sphere1 = new Sphere(12.), sphere2 = new Sphere(35.);
        List<Solid> solids = List.of(cube1, cube2, sphere1, sphere2);
        List<Cube> cubes = new ArrayList<>(List.of(cube1, cube2));
        List<Sphere> spheres = List.of(sphere1, sphere2);
        List<? extends Polyhedron> polys = cubes;

        Collections.sort(cubes);


        Point o = new Point(1., -1.5, 2.);
        for (Polyhedron poly : polys) {
            for (Point p : poly.at(o)) {
                System.out.println(p); //question mark?
            }
        }
    }


}

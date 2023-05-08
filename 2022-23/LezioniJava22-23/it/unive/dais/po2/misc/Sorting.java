package it.unive.dais.po2.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorting {

    public interface Shape extends Comparable<Shape> {
        double area();
        double perimeter();

        default int compareTo(Shape o) {
            return (int) (this.area() - o.area());
        }
    }

    public static class Circle implements Shape {
        private double ray;

        public Circle(double r) {
            this.ray = r;
        }

        @Override
        public int compareTo(Shape s) {
            if (s instanceof Circle) {
                Circle c = (Circle) s;
                return (int) (ray - c.ray);
            }
            else {
                return (int) (area() - s.area());
            }
        }

        @Override
        public double area() {
            return Math.PI * ray * ray;
        }

        @Override
        public double perimeter() {
            return Math.PI * 2. * ray;
        }
    }

    public static class Rectangle implements Shape {
        private double base, height;

        public Rectangle(double b, double h) {
            this.base = b;
            this.height = h;
        }

        @Override
        public double area() {
            return base * height;
        }

        @Override
        public double perimeter() {
            return 2. * base + 2. * height;
        }
    }

    public static void main(String[] args) {
        List<Integer> l = List.of(4, 1, 38, -4, -567);
        Collections.sort(l);    // questa sort() sfrutta il fatto che gli Integer implementano Comparable<Integer>

        List<Circle> l2 = new ArrayList<>();
        l2.add(new Circle(4.));
        l2.add(new Circle(40.));
        l2.add(new Circle(56.));
        Collections.sort(l2);   // questa sort() funziona perché Circle implementa Shape che implementa Comparable<Shape>

        // queste due sort() ignora il fatto che Cirlce implementi Comparable<Shape> e usa il Comparator passato
        // come secondo argomento: si badi che il Comparator vince sul Comparable
        Collections.sort(l2, (o1, o2) -> (int) (o1.ray - o2.ray));
        Collections.sort(l2, (Shape o1, Shape o2) -> (int) (o1.perimeter() - o2.perimeter()));
    }

    // le due Collections.sort hanno le seguenti firme (vedi doc del JDK)
    //static <T extends Comparable<? super T>> void sort(List<T> l)
    //static <T> void sort(List<T> l, Comparator<? super T> cmp)




}

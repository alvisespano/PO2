package it.unive.dais.po2.patterns.factory;

class Circle implements Shape {
    private final double ray;

    Circle(double ray) {
        this.ray = ray;
    }

    @Override
    public void draw() {
        System.out.println(String.format("Circle[ray:%g]", ray));
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

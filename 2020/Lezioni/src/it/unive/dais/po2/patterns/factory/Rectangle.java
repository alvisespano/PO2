package it.unive.dais.po2.patterns.factory;

class Rectangle implements Shape {

    private final double base, height;

    Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println(String.format("Rectangle[%gx%g]", base, height));
    }

    @Override
    public double area() {
        return base * height;
    }

    @Override
    public double perimeter() {
        return (base + height) * 2.;
    }
}

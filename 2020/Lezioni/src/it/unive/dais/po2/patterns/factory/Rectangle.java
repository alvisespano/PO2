package it.unive.dais.po2.patterns.factory;

class Rectangle implements Shape {

    private final double base, height;

    Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    /**
     * Metodo che scrive
     *  - la forma della figura (Rettangolo)
     *  - base
     *  - altezza
     */
    @Override
    public void draw() {
        System.out.println(String.format("Rectangle[%gx%g]", base, height));
    }

    /**
     * Metodo che calcola l'area del cerchio
     * @return area del rettangolo
     */
    @Override
    public double area() {
        return base * height;
    }

    /**
     * Metodo che calcola il perimetro del cerchio
     * @return perimetro del rettangolo
     */
    @Override
    public double perimeter() {
        return (base + height) * 2.;
    }
}

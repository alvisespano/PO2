package it.unive.dais.po2.patterns.factory;

class Circle implements Shape {
    private final double ray;

    Circle(double ray) {
        this.ray = ray;
    }

    /**
     * Metodo che scrive
     *  - la forma della figura (Cerchio)
     *  - raggio
     */
    @Override
    public void draw() {
        System.out.println(String.format("Circle[ray:%g]", ray));
    }

    /**
     * Metodo che calcola l'area del cerchio
     * @return area del cerchio
     */
    @Override
    public double area() {
        return Math.PI * ray * ray;
    }

    /**
     * Metodo che calcola il perimetro del cerchio
     * @return perimetro del cerchio
     */
    @Override
    public double perimeter() {
        return Math.PI * 2. * ray;
    }
}

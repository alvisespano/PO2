package it.unive.dais.po2.patterns.factory;

import org.jetbrains.annotations.NotNull;

/**
 * Estendo la ShapeFactory,
 * overrido la create
 * aggiungo amp
 */
public class ShapeFactoryWider extends ShapeFactory {

    private final double amp;

    /**
     * Costruttore
     * @param amp dato dell'amplificazione della forma
     */
    public ShapeFactoryWider(double amp) {
        this.amp = amp;
    }

    /**
     * Metodo che
     * quando si costruisce una ShapeFactoryWider le forme saranno ampliate di amp volte
     * @param name  nome forma
     * @param data  misure della forma
     * @return      l'implementazione della figura
     * @throws Exception ritornata perché la figura non è implementata o non presente
     */
    @Override
    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0] * amp, data[1] * amp);
        else if (name.toLowerCase().equals("circle"))
            return new Circle(data[0] * amp);
        else throw new Exception("invalid shape: " + name);
    }

}

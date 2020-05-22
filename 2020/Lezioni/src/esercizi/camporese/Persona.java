package esercizi.camporese;

public class Persona implements Comparable<Persona>, Cloneable {
    protected final String nome;
    public final int eta;

    public Persona(Persona p) {
        this.nome = p.nome;
        this.eta = p.eta;
    }

    public Persona(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    @Override
    public int compareTo(Persona other) {
        return eta - other.eta;
    }

    @Override
    public String toString() {
        return nome + ", " + eta;
    }

    @Override
    public Persona clone() {
        return new Persona(this);
    }

}

package esercizi.camporese;

public class Studente extends Persona {

    private int classe;
    private final int mediaVoti;

    public Studente(String nome, int eta, int classe, int mediaVoti) {
        super(nome, eta);
        this.classe = classe;
        this.mediaVoti = mediaVoti;
    }

    public Studente(Studente s) {
        super(s);
        this.classe = s.classe;
        this.mediaVoti = s.mediaVoti;
    }

    public int getClasse() { return classe; }

    public int getMediaVoti() {
        return this.mediaVoti;
    }

    public void prossimoAnno() {
        ++classe;
    }

    @Override
    public int compareTo(Persona o) {
        if (o instanceof Studente) {
            Studente other = (Studente) o;
            int diffClasse = classe - other.classe;
            return diffClasse == 0 ? eta - other.eta : diffClasse;
        }
        else return super.compareTo(o);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + classe + ", " + mediaVoti;
    }

    @Override
    public Studente clone() {
        return new Studente(this);
    }

}

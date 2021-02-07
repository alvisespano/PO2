package esercizi.camporese;

public class Laureando extends Studente {
    private int votoDiLaurea;

    public Laureando(String nome, int eta, int classe, int mediaVoti, int votoDiLaurea) {
        super(nome, eta, classe, mediaVoti);
        this.votoDiLaurea = votoDiLaurea;
    }

    public int compareTo(Laureando o) {
        int diff = votoDiLaurea - o.votoDiLaurea;
        return diff == 0 ? eta - o.eta : diff;
    }

    @Override
    public int compareTo(Persona o) {
        if (o instanceof Laureando) {
            return compareTo((Laureando) o);
        }
        else return super.compareTo(o);
    }

    /*
            if (o instanceof Studente) {
            Studente other = (Studente) o;
            int diffClasse = classe - other.classe;
            return diffClasse == 0 ? eta - other.eta : diffClasse;
        }
        else return super.compareTo(o);
     */
}

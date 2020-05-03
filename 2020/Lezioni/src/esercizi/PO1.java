package esercizi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PO1 {

    private static final Random rnd = new Random();

    /**
     * Classe Dangeon
     */
    public static class Dungeon {
        private final Room[][] rooms;
        private final int w, h;

        /**
         * Costruttore campo di gioco (matrice)
         * @param w larghezza
         * @param h altezza
         */
        public Dungeon(int w, int h) {
            rooms = new Room[w][h];
            this.w = w;
            this.h = h;

            // Creazione delle stanze
            for (int y = 0; y < h; ++y) {       // Righe
                for (int x = 0; x < w; ++x) {   // Colonne
                    rooms[x][y] = new Room();
                }
            }

            // Collego le stanze tra di loro
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    Room r = rooms[x][y];
                    r.n = getRoom(x, y - 1);    // Collego a nord
                    r.s = getRoom(x, y + 1);    // Collego a sud
                    r.o = getRoom(x - 1, y);    // Collego a ovest
                    r.e = getRoom(x + 1, y);    // Collego a est
                }
            }
        }

        /**
         * Funzione di appoggio a Dungeon che controlla
         * di non uscire dai bordi della matrice
         * @param x coordinata x
         * @param y coordinata y
         * @return Una stanza
         */
        public Room getRoom(int x, int y) {
            if (x < 0 || y < 0 || x >= w || y >= h) return null;
            return rooms[x][y];
        }

        /**
         * Reset del flag della stanza in modo tale che non è stata visitata
         */
        private void reset() {
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    rooms[x][y].visited = false;
                }
            }
        }


        /**
         * Dice il totale del valore dei tesori
         * @param x coordinata x
         * @param y coordinata y
         * @return  totale
         */
        public double maxScore(int x, int y) {
            reset();
            return visit(getRoom(x, y));
        }

        /**
         * Metodo che calcola il valore del tesoro
         * @param start stanza iniziale
         * @return      somma
         */
        private static double visit(@Nullable Room start) {
            if (start == null || start.visited) return 0.;  // se la stanza non c'è o la stanza è già stata visitata
            return start.getScore() + visit(start.n) + visit(start.s) + visit(start.e) + visit(start.o);
        }

    }

    public static void main(String[] args) {
        Dungeon dung1 = new Dungeon(3, 3);
        double score = dung1.maxScore(0, 0);
    }

    /**
     * Classe Room
     */
    public static class Room {
        @NotNull
        private final Collection<Treasure> treasures; // Collection che può rappresentare 0 o più oggetti Treasure
        @Nullable
        Room n, o, s, e;    // Rappresentazione delle 4 stanze (nord, ovest, sud, est)
        boolean visited = false;    // flag che indica se una stanza è già stata visitata

        /**
         * Costruttore che serve per inizializzare la prima stanza che prende solo i tesori
         * e chiama poi il costruttore fratello con tutti i parametri
         * @param treasures
         */
        public Room(@NotNull Collection<Treasure> treasures) {
            this(treasures, null, null, null, null);
        }

        /**
         * Costruttore che serve per inizializzare la prima stanza
         * richiamando il costruttore fratello passandogli tutti null
         */
        public Room() {
            this(null, null, null, null);
        }

        /**
         * Costruttore di una stanza, dati:
         * @param treasures tesoro
         * @param n         stanza a nord
         * @param o         stanza a ovest
         * @param s         stanza a sud
         * @param e         stanza a est
         */
        public Room(@NotNull Collection<Treasure> treasures, @Nullable Room n, @Nullable Room o, @Nullable Room s, @Nullable Room e) {
            this.treasures = treasures;
            this.n = n;
            this.o = o;
            this.s = s;
            this.e = e;
        }

        /**
         * Costruttore di una stanza, dati:
         * @param n         stanza a nord
         * @param o         stanza a ovest
         * @param s         stanza a sud
         * @param e         stanza a est
         * I treasure in questo caso non sono presenti nei parametri,
         * quindi vengono generati randomicamente.
         */
        public Room(@Nullable Room n, @Nullable Room o, @Nullable Room s, @Nullable Room e) {
            Collection<Treasure> ts = new ArrayList<>();
            for (int i = 0; i < rnd.nextInt(10); ++i)
                ts.add(new Treasure(rnd.nextInt(10), rnd.nextInt(50), rnd.nextInt(100)));
            this.treasures = ts;
            this.n = n;
            this.o = o;
            this.s = s;
            this.e = e;
        }

        public double getScore() {
            double r = 0.;
            for (Treasure t : treasures) {
                r += t.getValue();
            }
            return r;
        }

    }

    /**
     * Classe Treasure
     */
    public static class Treasure {
        /* Non serve mettere il @NotNull perché ci serve l'informazione dell'opzionalità, quindi
         * basta mettere a 0 la moneta che non è presente
         */
        private final int gold, silver, bronze;

        // Costruttore del tesoro
        public Treasure(int g, int s, int b) {
            this.gold = g;
            this.silver = s;
            this.bronze = b;
        }

        public double getValue() {
            return gold * 5. + silver * 2. + bronze;
        }
    }

}


/*
    La matrice di adiacenza esprime le relazioni tra i nodi di un grafo,
    ma non dice da che parte sono connessi (nord, sud, est, ovest).
 */

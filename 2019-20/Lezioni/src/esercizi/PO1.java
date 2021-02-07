package esercizi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PO1 {

    private static final Random rnd = new Random();

    public static class Dungeon {
        private final Room[][] rooms;
        private final int w, h;

        public Dungeon(int w, int h) {
            rooms = new Room[w][h];
            this.w = w;
            this.h = h;
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    rooms[x][y] = new Room();
                }
            }
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    Room r = rooms[x][y];
                    r.n = getRoom(x, y - 1);
                    r.s = getRoom(x, y + 1);
                    r.o = getRoom(x - 1, y);
                    r.e = getRoom(x + 1, y);
                }
            }
        }

        public Room getRoom(int x, int y) {
            if (x < 0 || y < 0 || x >= w || y >= h) return null;
            return rooms[x][y];
        }

        private void reset() {
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    rooms[x][y].visited = false;
                }
            }
        }

        public double maxScore(int x, int y) {
            reset();
            return visit(getRoom(x, y));
        }

        private static double visit(@Nullable Room start) {
            if (start == null || start.visited) return 0.;
            return start.getScore() + visit(start.n) + visit(start.s) + visit(start.e) + visit(start.o);
        }

    }

    public static void main(String[] args) {
        Dungeon dung1 = new Dungeon(3, 3);
        double score = dung1.maxScore(0, 0);
    }


    public static class Room {
        @NotNull
        private final Collection<Treasure> treasures;
        @Nullable
        Room n, o, s, e;
        boolean visited = false;

        public Room(@NotNull Collection<Treasure> treasures) {
            this(treasures, null, null, null, null);
        }

        public Room() {
            this(null, null, null, null);
        }

        public Room(@NotNull Collection<Treasure> treasures, @Nullable Room n, @Nullable Room o, @Nullable Room s, @Nullable Room e) {
            this.treasures = treasures;
            this.n = n;
            this.o = o;
            this.s = s;
            this.e = e;
        }

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

    public static class Treasure {
        private final int gold, silver, bronze;

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

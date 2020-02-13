import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

// RISPOSTE DEL TEST
// (1) 2
// (2) 1
// (3.a) 3
// (3.b) 1

public class Test {

    public static class Rpg {
        public static <A, B> Collection<B> map(Collection<A> c, Function<? super A, ? extends B> f) {   // rimuovere i wildcard per verificare che la chiamata nel main non compila [domanda 2]
            List<B> r = new ArrayList<>();
            for (A a : c) {
                r.add(f.apply(a));
            }
            return r;
        }

        public static abstract class Character<R extends Number> {
            public int level;
            public final String name;

            protected Character(int level, String name) {
                this.level = level;
                this.name = name;
            }

            public abstract R attack();
        }

        public static class Paladin extends Character<Float> {
            public float mana;

            public Paladin(int level, String name) {
                super(level, name);
                mana = 100.f;
            }

            // questo override è ovviamente valido perché il generic R è Float [domanda 1]
            @Override
            public Float attack() {
                return mana * level / 2.f;
            }
        }

        public static class Rogue extends Character<Number> {
            public int energy;

            public Rogue(int level, String name) {
                super(level, name);
                energy = 50;
            }

            // anche questo override è valido anche se il generic R è Number, perché Integer è sottotipo di Number ed i tipi di ritorno si possono specializzare [domanda 1]
            @Override
            public Integer attack() {
                return (energy -= 35) > 20 ? level * 2 : 0;
            }
        }

        // questi 2 metodi sono overload validi [domanda 3.a]
        public static int normalizeAttack(Character c) {
            return 1 + (int) c.attack();
        }

        public static Float normalizeAttack(Paladin c) {
            return c.attack();
        }



        public static void main() {
            List<Paladin> retadins = new ArrayList<>();
            retadins.add(new Paladin(60, "Leeroy Jenkins"));
            retadins.add(new Paladin(80, "Arthas"));

            // questa chiamata compila solo se ci sono i wildcard nella firma della map() [domanda 2]
            Collection<Number> a = map(retadins, new Function<Character, Float>() {
                @Override
                public Float apply(Character c) {
                    return 1.3f * (float) c.attack();

                }
            });

            // questa chiamata risolve normalizeAttack(Paladin) [domanda 3.b]
            Collection<Number> b = map(retadins, Rpg::normalizeAttack);

        }
    }
}


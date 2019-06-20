import java.util.*;

public class Es1 {

    public interface Equatable<T> {
        boolean equalsTo(T x);
    }

    public static class Person<P extends Person<P>> implements Equatable<P> {

        public final String name;
        public final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // 1.a
        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            // field comparison
            return equalsTo((P) o);
        }

        // 1.b
        @Override
        public boolean equalsTo(P other) {
            return name.equals(other.name) && age == other.age;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class Artist extends Person<Artist> {

        public final Hair hair;

        public Artist(String name, int age, Hair hair) {
            super(name, age);
            this.hair = hair;
        }

        // 1.d RISPOSTA: 4
        // 1.e
        @Override
        public boolean equalsTo(Artist other) {
            return super.equalsTo(other) && hair.equals(other.hair);
        }

    }

    public enum Color {
        BROWN, DARK, BLONDE, RED, GRAY;
    }

    public static class Hair implements Equatable<Hair> {
        public final int length;
        public final Set<Color> colors;

        public Hair(int length, Set<Color> colors) {
            this.colors = colors;
            this.length = length;
        }

        // 1.c
        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            // field comparison
            return equalsTo((Hair) o);
        }

        // 1.c
        @Override
        public boolean equalsTo(Hair x) {
            return colors.equals(x.colors) && length == x.length;
        }
    }


    public static void print(String fmt, Object... o) {
        System.out.println(String.format(fmt, o));
    }

    public static void main(String[] args) {
        Person alice = new Person("Alice", 23),
                david = new Artist("Bowie", 69, new Hair(75, Set.of(Color.RED, Color.BROWN, Color.GRAY)));

        Artist morgan = new Artist("Morgan", 47, new Hair(20, Set.of(Color.GRAY, Color.DARK))),
                madonna = new Artist("Madonna", 60, new Hair(50, Set.of(Color.BLONDE)));

        // 1.g (lanciate il programma per printare le risposte booleane)
        List<Boolean> bs = Arrays.asList(
                alice.equals(null),         // false
                alice.equals(alice),        // true
                //null.equals(david),       // alcuni compilatori rifiutano null.metodo(), altri sì ma a runtime viene lanciato NullPointerException
                alice.equals(david),        // false
                alice.equalsTo(morgan),     // false
                morgan.equals(morgan),      // true
                morgan.equals(madonna),     // false
                morgan.equals(david),       // false
                //morgan.equalsTo(david),   // non compila
                david.equalsTo(morgan),     // false
                madonna.equals(3)           // false
                //, madonna.equalsTo("Madonna") // non compila
        );
        print("[1.g] %s", bs);

        // 1.h
        List<Artist> artists = Arrays.asList((Artist) david, morgan, madonna);
        List<Person> persons = Arrays.asList(alice, david, morgan, madonna);

        // 1.h.i
        Artist a = Collections.max(artists, new Comparator<Artist>() {
            @Override
            public int compare(Artist a, Artist b) {
                return a.hair.length * a.hair.colors.size();
            }
        });
        print("[1.h.i] %s", a);

        // 1.h.ii
        Person b = Collections.max(persons, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.name.compareTo(b.name);
            }
        });
        print("[1.h.ii] %s", b);

        // 1.h.iii: RISPOSTA: 2
        Artist c = Collections.max(artists, new Comparator<Person>() {
            public int compare(Person a, Person b) {
                return a.age - b.age;
            }
        });

    }


}

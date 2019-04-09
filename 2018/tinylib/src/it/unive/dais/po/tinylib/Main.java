package it.unive.dais.po.tinylib;

import java.util.Random;

public class Main {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        try {
            test1();
            test2();
        } catch (Throwable e) { // blindiamo il main facendo in modo che nessuna eccezione possa uscire
            log("Exception caught: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // funzioni di test
    //

    // testa i metodi di MyList e alla fine verifica che una operazione di rimozione fallisca
    public static void test1() throws Exception {
        MyList<Integer> l = new MyNodeList<>();
        populate(l, 10);
        // stampiamo la lista appena popolata
        print(l);
        // facciamo un po' di operazioni di modifica della lista
        l.insertAt(1, 11);
        l.removeAt(10);
        l.removeHead();
        l.removeHead();
        // ristampiamo la lista per vedere le differenze
        print(l);

        // ora facciamo una operazione che sappiamo dovrebbe fallire
        try {
            l.removeAt(9);     // in posizione 9 non dovrebbe esserci più un elemento, quindi questa riga dovrebbe lanciare una NotFoundException
            log("test1 failed");    // se arriva ad eseguire questa linea, significa che la removeAt() qui sopra non è fallita come avrebbe dovuto
        } catch (NotFoundException e) {
            log("test1 succeded");  // se viene lanciata l'eccezione allora vuol dire che la removeAt() è fallita, quindi il test ha avuto successo
        }
    }

    public static void test2() {
        MyList<Integer> l = new MyNodeList<>();
        populateRandom(l, 10);
        // stampiamo la lista appena popolata
        print(l);
        // sommiamo gli elementi
        int r = sum(l); // questa chiamata risolve l'overload della sum che prende un MyIterable
        log(String.format("sum = %d", r));
        log("test2 succeded");
    }

    // operazioni varie sulle liste
    //

    // somma tutti gli interi forniti da un iteratore
    public static int sum(MyIterator<Integer> it) {
        int sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        return sum;
    }

    // somma tutti gli elementi interi di un MyIterable usando la sum() qui sopra
    public static int sum(MyIterable<Integer> l) {
        return sum(l.iterator());
    }

    // popola l con n interi crescenti da 0 a n-1
    public static void populate(MyList<Integer> l, int n) {
        for (int i = 0; i < n; i++) {
            l.insertHead(i);
        }
    }

    // popola l con n interi casuali tra 0 e n-1
    public static void populateRandom(MyList<Integer> l, int n) {
        for (int i = 0; i < n; i++) {
            l.insertHead(rand.nextInt(n));
        }
    }

    // pretty printers
    //

    public static void print(MySequence<?> l) {
        StringBuilder b = new StringBuilder();
        try {
            for (int i = 0; i < l.size(); i++) {
                // il metodo getAt() può lanciare NotFoundException, ma noi facciamo il catch FUORI dal ciclo
                b.append(l.getAt(i)).append(" ");
            }
            log(String.format("MySequence:\n\tsize: %d\n\tcontent: %s", l.size(), b));
            // facendo il catch qui, se getAt() fallisce interrompiamo il ciclo e scriviamo un messaggio d'errore
        } catch (NotFoundException e) {
            log("Exception caught during print: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void print(MyIterator<?> it) {
        StringBuilder b = new StringBuilder();
        int size = 0;   // l'iteratore non sa a priori il numero di elementi che itererà, quindi ci tocca contarli per stampare la size
        while (it.hasNext()) {
            b.append(it.next());
            size++;
        }
        System.out.println(String.format("MySequence:\n\tsize: %d\n\tcontent: %s", size, b));
    }

    public static void print(MyIterable<?> i) {
        print(i.iterator());    // semplice richiamo della versione appena sopra
    }


    // versione alternativa che stampa anche il tipo dell'elemento della sequenza estraendo l'informazione a runtime via reflection
    public static <E> void printWithType(MySequence<E> l) {
        StringBuilder b = new StringBuilder();
        try {
            Class<?> cl = null;
            for (int i = 0; i < l.size(); i++) {
                E x = l.getAt(i);   // se voglio mettere l'elemento i-esimo in una variabile, devo metterlo in una variabile di tipo T
                cl = x.getClass();  // estraggo il tipo dell'elemento usando la reflection: lo faccio solo perché qui sotto stampiamo il tipo, altrimenti non servirebbe!
                b.append(String.format("%s ", x));    // la format string '%s' stampa l'elemento x di tipo T invocando il suo toString()
            }
            System.out.println(String.format("MyList:\n\ttype: %s\n\tsize: %d\n\tcontent: %s", cl == null ? "unknown" : cl.getName(), l.size(), b));
        } catch (NotFoundException e) {
            log("Exception caught during print: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // shortcut per stampare in stdout
    public static void log(String s) {
        System.out.println(s);
    }
}

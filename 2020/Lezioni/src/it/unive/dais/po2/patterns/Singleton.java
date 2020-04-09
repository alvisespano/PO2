package it.unive.dais.po2.patterns;

/*
* Nullable e NotNull sono definite dalla stessa libreria
*
* Si usano su  un campo, su una variabile o su un metodo.
*
* NOTNULL:  significa che quel campo/variabile/metodo
*           non possono mai essere null (per il metodo il ritorno non può essere null).
*
* NULLABLE: serve a dire al compilatore che questo campo può avere anche null come valore.
*           Siccome è stato specificato che quel campo può essere null, allora ogni volta
*           che si usa il campo, il compilatore ti ricorda di fare un if.
*/
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class Singleton {
    // Screen è un oggetto che non ha senso costruire più di una volta
    public static class Screen {}

    public static class Display {
        // eventuali campi privati non-statici
        //private Screen screen; // screen wrappato da display

        /*
         * Campo statico di tipo Display
         */
        @Nullable
        private static Display instance = null;

        /**
         * Costruttore di display privato
         */
        private Display() {
            // inizializzazione di tutti i tuoi campi privati non-statici
            // es this.screen = ...
        }

        /**
         * Metodo getInstance è il metodo che crea l'istanza una volta sola (create)
         * @return
         */
        @NotNull // non voglio che getInstance() ritorni NULL ma che sia sempre un Display
        public static Display getInstance() {
            if (instance == null) {
                instance = new Display();
            }
            return instance;
        }

    }

    public static void main(String[] args) {
        // nessuno mi vieta di creare più display.
        Display d = Display.getInstance();
        Display d2 = Display.getInstance(); // non viene creato un altro display
    }


}


/*
 * Un Singleton è una tecnica per fare in modo che un oggetto si costruisca al massimo una volta
 * e non ci siano più di una istanza.
 * Esempi di oggetti che si costruiscono al massimo una volta sono:
 *  - connessione al DB
 *  - display del computer.
 *
 * Quando ho bisogno di evitare che l'utente istanzi più di una volta qualcosa che non ha
 * senso istanziare più di una volta allora esiste una tecnica per fare questa cosa,
 * pratica di programmazione Singleton ed è un pattern.
 *
 * Nessuno vieta di creaere più oggetti di tipo display, però bisogna in qualche modo vietare
 * che ciò accada, nel senso che bisogna fare in modo con il linguaggio che un programmatore
 * non possa sbagliarsi (mettere un costruttore non fa al caso nostro perché permette di costruire
 * molteplici volte).
 * Per evitare ciò si rende privato il costruttore di Display, che significa che c'è,
 * ma che non può essere chiamato da chi è fuori dalla classe
 * (un membro privato significa che può essere acceduto solamente dai membri della classe).
 *
 * A cosa serve un costruttore privato se da fuori non si può costruire Display?
 * Faccio un campo statico (instance) di tipo Display, cioè dentro la classe Display, che voglio
 * evitare che sia costruita più volte, ci metto:
 *  1) il costruttore privato
 *  2) ci faccio un campo statico dello stesso tipo.
 * Faccio un metodo pubblico statico (getInstance) e questo metodo dice che:
 *  - se il campo statico (instance) è nullo, allora chiama il costruttore, perché lui può,
 *    in quanto è interno alla classe
 *  - altrimenti ritorno instance
 * Quindi nel main non chiamo il costruttore di display, ma chiamo il metodo pubblico (getInstance)
 * e questo metodo si può chiamare quante volte si vuole (crea un solo oggetto, alla prima chiamata).
 *
 * Questa tecnica serve a fare in modo che l'oggetto di tipo Display si possa costruire,
 * ma una volta sola. Per vietare che sia costruito oltre la prima volta:
 *  - chiudiamo la porta al costruttore (private), quindi bisogna usare un metodo statico per costruire,
 *    che è lui che chiama il costruttore se l'oggetto non è ancora stato istanziato, altrimenti ritorna
 *    l'istanza.
 *
 * Ecco perché questo pattern si chiama Singelton: al massimo c'è/viene_creato un solo oggetto.
 *
 */

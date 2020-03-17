package it.unive.dais.po2.myjdk;

/**
 * Un set:
 *   - è una struttura dati;
 *   - non prevede un'ordine preciso degli elementi;
 *   - non prevede l'accesso tramite un indice (si possono solo iterare gli elementi dell'insieme).
 *   - non ci sono elementi duplicati.
 *
 * Questa classe estende MyCollection ma non ha niente a che vedere
 * con la classe MyList
 * @param <T>
 */
public interface MySet<T> extends MyCollection<T> {

    /*
    * Un set non aggiunge niente a una Collection
    * la estende ma non aggiunge nulla.
    * Serve solo a diversificare un Set da una Collection,
    * per definizione di Set.
    * Ho solo un nome nuovo per Collection.
    */
}

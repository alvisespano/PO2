package it.unive.dais.po2.myjdk;

public class TestMap {

    public static void main(String[] args) {

        try {
            MyMap<String, Integer> m = new MyIdentityMap<>(); // basta cambiare quest'ultimo MyIdentityMap in MyHashMap se voglio usare le hashMap
            m.put("Alvise", 42);
            m.put("Francesco", 12);
            m.put("Gianni", 56);
            m.put("Pippo", 78);

            // let tura all'interno della mappa
            int eta_di_gianni = m.get("Gianni");
            System.out.println("Risultato: " + eta_di_gianni);

             m.clear(); // <- se subsumo con HashMap non ho la clear(), se dò MyHashMap non ho problemi

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}

/*
*
* HashMap: ha dei metodi specifici che la identificano come hashMap.
*          Fa una cosa molto particolare:
*          il confronto che fa con le chiavi, è fatto con l'hash code dell'oggetto,
*          non usando equals.
*          Una HashMap
*          usa al suo interno un array,
*          usa il codice hash delle chiavi come posizione nell'array.
*
*
* MyMap contiene solo i metodi generali (che devono avere le mappe).
* Map: sono concetti generali di strutture dati associative.
*      Permettono di memorizzare data una chiave un valore,
*      poi per leggerci dentro bisogna avere la chiave e viene dato il valore.
*      Il senso della mappa è quello di inserire coppie
*      e risalire al secondo dato il primo elemento.
*
* E' buona norma subsumere all'interfaccia che serve di più,
* in modo tale che se servono si possono usare tutti i metodi dell'interfaccia
* e non solo quelli implementati nella specifica classe.
*
* Si subsume a MyMap piuttosto che MyHashMap
* è per poter cambiare il tipo dell'oggetto che si costruisce
* piuttosto che cambiare tutto il resto del codice.
 */

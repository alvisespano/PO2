package it.unive.dais.po2.zoo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Dog fido = new Dog(15, "bruno");
        Dog baldo = new Dog(20, "bianco");

        List<Dog> dogs = new ArrayList<>();
        dogs.add(fido);
        dogs.add(baldo);

        /*
        List<Animal> l3 = new ArrayList<Dog>();
        l3.add(new Cat(10, "idfif"));

        // wildcards

        List<? extends Animal> l = new ArrayList<>();
        Animal a1 = l.get(0);

        l.set(0, new Dog(10, "fido"));
        l.add(new Dog(10, "fido"));


        List<? super Dog> l2 = new ArrayList<>();
        Animal a2 = l2.get(0);

        l2.set(0, new Dog(10, "fido"));
        l2.set(0, new Animal(10));
        l2.add(new Dog(10, "fido"));

        Function<Animal, Cat> f1 = (Animal s) -> new Cat(s.weight, "blu");
        Function<? super Dog, ? extends Animal> f = f1;*/



        // TODO: provare a mettere a posto in modo che compili
/*        Animal jackie = new Dog(2, "nero");
        Dog pluto = new Animal(50);
        Animal selene = new Cat(4, "grigio");

        // sumsumption NON valide
        Dog fido2 = selene;
        Cat selene2 = selene;

        Cat tigre = new Cat(20);
        Animal cane = new Dog(50, tigre);
        Animal p = fido.getPartner();

        selene.eat(fido);

        Animal a = new Persian(6, fido);
        a.meow(); // NON COMPILA
        a.eat(fido);

        a.eat(a);   // mangiare se stessi si può fare
*/

    }

}



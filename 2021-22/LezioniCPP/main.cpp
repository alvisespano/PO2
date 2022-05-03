#include <iostream>

import pairs;
import sums;
import zoo;

// per C++20 ALMENO una definizione di gianni() deve esistere PRIMA della funzione templatizzata che la usa (questo progetto compila in C++20)
// in C++ vanilla poteva non esisterne neanche una
int gianni(int x) { return x + 1; }


template <class Gigi>
Gigi f(const Gigi& gigi)
{
	return gianni(gigi);	// da C++20 i template sono type-checkati e una funzione di nome gianni deve esistere ALMENO di un tipo
}


int main()
{

	int x = f(3);
	//char* s = f("ciao");	// questa NON compila perché non esiste un overload di gianni() che prenda char*

	return 0;
}


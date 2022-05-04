#include <iostream>

import pairs;
import sums;
import zoo;

// da C++11 ALMENO una definizione di gianni() deve esistere PRIMA della funzione templatizzata che la usa (questo progetto compila in C++20)
// in C++ vanilla poteva non esisterne neanche una
int gianni(int x) { return x + 1; }


template <typename Gigi>
Gigi f(const Gigi& gigi)
{
	return gianni(gigi);	// da C++11 i template sono type-checkati e una funzione gianni() deve esistere ALMENO di un tipo
							// ma nemmeno C++20 può sapere con quale template argument verrà usata f() e quindi 
}


int main()
{

	int x = f(3);
	//char* s = f("ciao");	// questa NON compila perché non esiste un overload di gianni() che prenda char*

	return 0;
}


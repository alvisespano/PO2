#include <iostream>

import pairs;
import sums;

// da C++11 ALMENO una definizione di ident() deve esistere PRIMA della funzione templatizzata che la usa (questo progetto compila in C++20)
// in C++ vanilla poteva non esisterne neanche una
int ident(int x) { return x; }


template <class T>
T dual_ident(const T& a)
{
	return ident(ident(a));	// da C++11 i template sono type-checkati debolmente: viene controllato solamente che esista ALMENO una definizione per le funzioni usate internamente
							// ma il compilatore non può sapere con quale template argument verrà invocata questa funzione, quindi sospende la compilazione
}

export int test_ident()
{

	// ogni singola chiamata ad una funzione templatizzata genera una nuova istanza della funzione in cui viene sostituito il template parameter con il tipo
	//   concreto usato in quel caso; la funzione viene poi compilata con i tipo completo.
	[[maybe_unused]] int x = dual_ident(3);	// questa invocazione genera una istanza della funzione dual_ident() con int come template argument
							// tale istanza compila perché esiste una definizione di ident() che prende e ritorna int

	//char* s = dual_ident("ciao");	// questa invocazione genera una istanza della funzione dual_ident() con char* come template argument
									// ma questa istanza NON compila perché non esiste un overload di ident() che prenda char*

	return 0;
}


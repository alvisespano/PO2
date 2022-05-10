
export module iterators;

import <iostream>;
import <vector>;
import <list>;
import <string>;
import pairs;
import smart_ptr;

using namespace std;


// questa funzione è templatizzata su un tipo Container QUALUNQUE
// non c'è alcun genere di specifica dei requisiti che questo tipo Container deve rispettare: il nome stesso è totalmente arbitrario
// l'implementazione fa emergere la necessità che questo tipo Container risponda a certi requisiti
template <class Container>
void increment_all(Container& v)
{
	// la keyword typename indica al compilatore che l'identificatore che segue è un TIPO e non un membro statico
	// senza typename il compilatore potrebbe confondere Container::iterator con un campo statico di nome iterator dentro la classe Container
	for (typename Container::iterator it = v.begin(); it != v.end(); ++it)	// richiede l'esistenza di un member type di nome iterator e dei metodi begin() ed end()
	{																		// inoltre richiede l'operatore di post-incremento sul tipo dell'iteratore
		typename Container::reference v = *it;	// richiede l'esistenza di un member type di nome reference (di solito è equivalente a value_type&)
		++v;									// richiede l'esistenza dell'operatore di pre-incremento per il tipo dell'elemento del container
	}
}

template <class Container>
void print_all(Container& v)
{
	cout << typeid(Container).name() << "[";
	for (typename Container::const_iterator it = v.begin(); it != v.end(); ++it)	// usiamo il const_iterator perché non dobbiamo modificare gli elementi del container
	{
		cout << *it << ", ";	// richiede l'operatore << sul tipo dell'elemento del container
	}
	cout << "\b\b]" << endl;
}

using pairs::mypair;

// definiamo gli operatori di output stream per smart_ptr e mypair
template <class A, class B>
ostream& operator<<(ostream& os, const mypair<A, B>& p)
{
	return os << "(" << p.fst() << ", " << p.snd() << ")";
}

// ridefinisce l'operatore GLOBALE di streaming per double*
ostream& operator<<(ostream& os, const double*& p)
{
	os << "&" << static_cast<const void*>(p);			// casta a constvoid* per stampare l'indirizzo numerico (senza cast sarebbe una RICORSIONE!!!!)
	if (p != nullptr) os << "[" << *p << "]";			// se non è nullo printa anche il dereference
	return os;
}

export void test_iterators()
{
	vector<int> v{ 1, 2, 3, 4 };
	print_all(v);		// questa chiamata genera una istanza della funzione print_all() con vector<int> sostituo al template parameter Container
	increment_all(v);	// questa chiamata genera una istanza della funzione print_all() con vector<int> sostituo al template parameter Container
	print_all(v);		// questa NON rigenera un'altra istanza della funzione print_all() per vector<int>: per ogni tipo diverso ne viene generata UNA SOLA

	list<string> l1{ "ciao", "sono", "io" };
	print_all(l1);			// questa chiamata genera una istanza di print_all() per list<string>
	//increment_all(l1);	// NON COMPILA: questa chiamata genera una istanza di increment_all() per list<string>, ma il pre-incremento non esiste per string

	double arr[] = {11.23, 35.53};
	list<mypair<unsigned int, double*>> l2{ {1u, arr}, {2u, arr}, {3u, arr} };
	print_all(l2);		// questa genera una istanza della print_all() per list<mypair<unsigned int, double*>>
						// essa compila perché abbiamo definito l'operator<< per mypair, altrimenti non compilerebbe
	increment_all(l2);	// questa compila perché mypair ha l'operatore di pre-incremento, il quale invoca a sua volta il pre-incremento sui due campi della coppia
						// pertanto la chiamata compila perché sia unsigned int che double* supportano il pre-incremento
	print_all(l2);
}
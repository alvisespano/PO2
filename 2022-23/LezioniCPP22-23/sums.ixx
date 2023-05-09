export module sums;

import <string>;
import <vector>;
import <functional>;
import <iostream>;

export namespace sums {

	// questa versione della sum templatizza l'intero container
	template <class C>
	typename C::value_type sum(const C& v)
	{
		if (v.size() > 0)
		{
			typename C::value_type r(v[0]);
			for (size_t i = 0; i < v.size(); ++i)
			{
				typename C::const_reference x(v[i]);
				r += x;
			}
			return r;
		}
		return C::value_type();
	}

	// questa versione della sum funziona sugli iteratori, naturalmente templatizzati
	// tuttavia assume l'esistanza del member type value_type, quindi non dovrebbe funzionare con i pointer a meno di usare i traits
	template <class InputIterator>
	typename std::iterator_traits<InputIterator>::value_type sum(InputIterator first, InputIterator last)
	{
		// iterator_traits<T> funziona come una type function: crea un tipo con tutti i member type che lo rendono compatibile con un iteratore
		// se gli si passa un tipo che è già un iteratore, non fa niente; se gli si passa un pointer crea tutti i member type opportuni
		typename std::iterator_traits<InputIterator>::value_type r(*first);
		while (first != last)
		{
			r += *first++;
		}
		return r;
	}

	// in C++11 è possibile usare auto
	template <class InputIterator>
	auto sum_cxx11(InputIterator first, InputIterator last) 
	{
		auto r(*first);
		while (first != last)
		{
			r += *first++;
		}
		return r;
	}

	// questa versione della sum ha 3 parametri: il terzo parametro è una funzione, il cui tipo è templatizzato, e 
	template <class InputIterator, class BinFun>
	auto sum(InputIterator first, InputIterator last, BinFun f)
	{
		auto r(*first);
		while (first != last)
		{
			r = f(r, *first++);	// f deve supportare la sintassi di CHIAMATA A FUNZIONE con 2 argomenti 
		}
		return r;
	}

	// questa classe definisce un overload di operator(), quindi permette alle sue istanze di supportare la sintassi della chiamata a funzione
	class my_binary_function_object
	{
	private:
		double k;

	public:
		explicit my_binary_function_object(double k_) : k(k_) {}

		// questo è l'operatore che permette alle istanze di questa classe di essere usate COME SE FOSSERO UNA FUNZIONE che prende 2 argomenti di tipo e ritorna un int
		double operator()(double a, double b) const 
		{ 
			return a + b + k; // somma i 2 argomenti e il campo
		}

		// questo metodo statico non serve a niente, se non a mostrare un piccolo test di questa classe per capire come funziona
		static void test()
		{
			my_binary_function_object f(7.1);	// costruisci un oggetto invocando l'unico costruttore che c'è
			double x = f(11.23, 35.0);			// la variabile f può essere usata COME SE FOSSE UNA FUNZIONE, cioè applicando 2 argomenti alla sua destra tra parentesi tonde
		}
	};

	int my_global_binary_function(char a, char b) { return a + b; }

	export void test()
	{
		std::vector<int> v1 { 1, 2, 3, -4, 5 };
		std::vector<double> v2 { 56.67, 0.0, 4.5, 98.2134 };
		char a[4] { 11, 24, 123, -23 };

		// alcuni test delle funzioni sum
		{
			int x = sum(v1);
			double y = sum(v2);
		}
		
		// con gli iteratori
		{
			int x = sum(v1.begin(), v1.end());
			double y = sum(v2.begin(), v2.end());
		}
		
		// con i pointer
		{
			char x = sum(a, a + 10);
			double b[5];
			double y = sum(b, b + 5);
		}

		// testiamo ora la versione della sum che prende 3 argomenti, di cui il terzo deve supportare la sintassi di chiamata a funzione
		// ci sono varie espressioni possibili che supportano la sintassi di chiamata funzione e che sono quindi compatibili con la sum in questione
		{
			int x = sum(v1.begin(), v1.end(), [](int a, int b) { return a + b; });	// una lambda binaria che la fa somma degli argomenti di tipo int
			int y = sum(v2.begin(), v2.end(), my_binary_function_object(8.67));		// una istanza di un oggetto che supporta la sintassi di chiamata con 2 argomenti
			int z = sum(a, a + 10, my_global_binary_function);						// un puntatore ad una funzione globale
		}

		
	}

}
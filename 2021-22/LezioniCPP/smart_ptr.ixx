export module smart_ptr;

import <string>;
import <vector>;

using namespace std;

export
template <class T>
class smart_ptr
{
private:
	T* pt;
	bool is_array;

	using counter_t = unsigned int; //	equivalente a scrivere: typedef unsigned int counter_t;

	counter_t* cnt;

	void destroy()
	{
		if (--(*cnt) == 0)
		{
			if (is_array) delete pt;
			else delete[] pt;
			delete cnt;
		}
	}

public:
	using value_type = T;		// equivalente a scrivere: typedef T value_type

	smart_ptr(T* pt_, bool is_array_ = false) : pt(pt_), cnt(new unsigned int(1)), is_array(is_array_) {}

	smart_ptr(size_t len) : smart_ptr(new T[len], len > 1) {}

	smart_ptr(const smart_ptr<T>& p) : pt(p.pt), cnt(p.cnt), is_array(p.is_array)
	{
		++(*cnt);
	}

	~smart_ptr()
	{
		destroy();
	}

	smart_ptr<T>& operator=(const smart_ptr<T>& p)
	{
		++(*p.cnt);
		destroy();
		pt = p.pt;
		cnt = p.cnt;
		return *this;
	}

	T& operator*()
	{
		return (*this)[0];
	}

	const T& operator*() const
	{
		return *pt;
	}

	T& operator[](size_t i)
	{
		return pt[i];
	}

	const T& operator[](size_t i) const
	{
		return pt[i];
	}

	T* operator->()
	{
		return pt;
	}

	const T* operator->() const
	{
		return pt;
	}

	// TODO STUDENTI: implementare operatori +, ++, +=, -, --, -=
	// attenzione: postare il pointer interno quando si incrementa/decrementa/ecc non è sufficiente, altrimenti la delete verrà invocata su un indirizzo sbagliato
	// consiglio1: l'aritmetica dei puntatori ha senso supportarla solamente quando is_array è true
	// consiglio2: tenere una copia del pointer ORIGINALE passato al costruttore che poi useremo per la delete; e spostare in avanti/indietro l'altro
};


// questa funzione templatizzata swappa cose qualunque, a patto che siano de-referenziabili e che il tipo del valore puntato sia assegnabile
template <class Pointer>
void swap_any_pointer(Pointer& p1, Pointer& p2)
{
	typename Pointer::value_type& tmp = *p1;	// richiede anche l'esistenza di un member type di nome value_type che indichi il tipo del valore puntato
	*p1 = *p2;
	*p2 = tmp;
}

export void test_smart_ptr()
{
	int* a = new int[100];
	smart_ptr<int> a2(a, true);	// ora l'array puntato da a è di proprietà dello smart_ptr, quindi non servirà fare 'delete[] a'
	smart_ptr<double> b(600);	// utilizza il costruttore che costruisce più di un istanza dello heap
	b[2] = a[3];				// supportano l'operatore di subscript

	string* s1 = new string("ciao");
	smart_ptr<string> s2 = s1;
	s2 = s1;    // questo assegnamento converte automaticamente il right value di tipo string* in un temporary object di tipo smart_ptr<string>
	s2 = s2;	// supporta anche l'assegnamento di sé stesso

	smart_ptr<vector<double>> v1(new vector<double>(10, 1.));
	smart_ptr<vector<double>> v2(new vector<double>(20, 2.));
	swap_any_pointer(v1, v2);

	int u = 3, w = 4;
	//swap_any_pointer(&u, &w);	// NON COMPILA perché int* non ha un member type di nome value_type come richiesto dalla funzione templatizzata

}




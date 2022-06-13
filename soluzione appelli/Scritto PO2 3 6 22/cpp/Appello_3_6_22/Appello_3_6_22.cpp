
// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 3/6/2022 per ciò che riguarda il quesito 6, ovvero la domanda che coinvolge C++.
// I quesiti 1-5 riguardanti Java sono in un progetto IntelliJ a parte, non qui.
// Il codice C++ qui esposto è standard C++ vanilla (a.k.a. C++03), sebbene il progetto VS sia configurato con il compilatore di default C++14

#include <iostream>
#include <vector>

using namespace std;

// 6
template <class T>
class matrix
{
private:
	size_t cols;
	vector<T> v;

public:
	matrix() : cols(0), v() {}
	matrix(size_t rows, size_t cols_) : cols(cols_), v(rows * cols) {}
	matrix(size_t rows, size_t cols_, const T& v) : cols(cols_), v(rows* cols, v) {}
	matrix(const matrix<T>& m) : cols(m.cols), v(m.v) {}

	typedef T value_type;
	typedef typename vector<T>::iterator iterator;
	typedef typename vector<T>::const_iterator const_iterator;

	matrix<T>& operator=(const matrix<T>& m)
	{
		v = m.v;
		return *this;
	}

	T& operator()(size_t i, size_t j)
	{
		return v[i * cols + j];
	}

	const T& operator()(size_t i, size_t j) const
	{
		return (*this)(i, j);
	}

	iterator begin()
	{
		return v.begin();
	}

	iterator end()
	{
		return v.end();
	}

	const_iterator begin() const
	{
		return begin();
	}

	const_iterator end() const
	{
		return end();
	}
};


int main()
{
	matrix<double> m1;          // non inizializzata
	matrix<double> m2(10, 20);  // 10*20 inizializzata col default constructor di double
	matrix<double> m3(m2);      // costruita per copia 
	m1 = m2;                    // assegnamento
	m3(3, 1) = 11.23;           // operatore di accesso come left-value 

	for (typename matrix<double>::iterator it = m1.begin(); it != m1.end(); ++it) {
		typename matrix<double>::value_type& x = *it;	// de-reference non-const
		x = m2(0, 2);									// operatore di accesso come right-value
	}
	
	matrix<string> ms(5, 4, "ciao"); // 5*4 inizializzata col la stringa passata come terzo argomento
	for (typename matrix<string>::const_iterator it = ms.begin(); it != ms.end(); ++it)
		cout << *it;				// de-reference const
}

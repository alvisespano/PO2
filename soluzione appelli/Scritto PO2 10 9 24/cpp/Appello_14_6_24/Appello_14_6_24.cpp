
// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 5/9/2023 per ciò che riguarda il quesito 2, ovvero la domanda che coinvolge C++.
// I quesiti 1-5 riguardanti Java sono in un sorgente Java a parte, non qui.
// Il codice C++ qui esposto è standard C++ vanilla (a.k.a. C++03), sebbene il progetto VS sia configurato con il compilatore di default C++14

#include <iostream>
#include <vector>

using namespace std;

template <class Container>
ostream& operator<<(ostream& os, const Container& c) 
{
	os << "[";
	for (typename Container::const_iterator it = c.begin(); it != c.end(); ++it)
		os << " " << *it;
	os << "]";
	return os;
}

template <class Container>
typename Container::value_type sum(const Container& c)
{
	typename Container::value_type r;
	for (typename Container::const_iterator it = c.begin(); it != c.end(); ++it)
	{
		r += *it;
	}
	return r;
}


int main()
{
}

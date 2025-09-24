// Appello_15_9_25.cpp 
//

#include <iostream>
#include <vector>

using namespace std;

template <class InputIterator>
typename InputIterator::value_type average(InputIterator begin, InputIterator end)
{
	typename iterator_traits<InputIterator>::value_type r{};	// chiamiamo esplcitamente il costruttore di default con la sintassi di C++14 (perché MSVS non supporta più il vanilla)
	//anche InputIterator::value_type andava bene

	size_t n = 0;
	for (; begin != end; ++n)
		r += *begin++;	// il value_type deve avere l'operatore +=
	if (n == 0)
		throw runtime_error("average of empty range");
	return r / n;	// e l'opereratore / con size_t some secondo argomento
}


int main()
{
	vector<int> v = { 1, 2, 3, 4, 5 };
	cout << average(v.begin(), v.end()) << endl;
}


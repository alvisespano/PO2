export module sums;

import <string>;
import <vector>;
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

	// in C++14 e superiore è possibile usare auto
	template <class InputIterator>
	auto sum_cxx14(InputIterator first, InputIterator last) -> decltype(*first)
	{
		auto r(*first);
		while (first != last)
		{
			r += *first++;
		}
		return r;
	}

	struct S 
	{
		int k;

		S(int k_) : k(k_) {}

		void operator()(const int& x)
		{
			std::cout << x;
		}

		int operator()(int a, int b) { return a + b; }
	};

	void test()
	{
		S x;
		x(6);
	}


	template <class InputIterator, class BinFun>
	auto sum(InputIterator first, InputIterator last, BinFun f)
	{
		auto r(*first);
		while (first != last)
		{
			r = f(r, *first++);
		}
		return r;
	}

	int global_plus(int a, int b) { return a + b; }

	export void test()
	{
		std::vector<int> v1{ 1, 2, 3 };

		int w = sum(v1.begin(), v1.end(), [](int a, int b) { return a + b; });
		int v = sum(v1.begin(), v1.end(), global_plus);
		int z = sum(v1.begin(), v1.end(), S(8));


		int x = sum(v1);
		std::vector<int> v2{ 234, 456,4567, 7 };
		int y = sum(v2);
		std::vector<double> v3{ 0.0 };
		double z = sum(v3);

		// con gli iteratori

		int n = sum(v1.begin(), v1.end());
		double n2 = sum(v3.begin(), v3.end());

		// con i pointer
		char a[10];
		char m = sum(a, a + 10);

		double b[5];
		double p = sum_cxx14(b, b + 5);

	}

}
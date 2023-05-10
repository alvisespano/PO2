export module lambdas;

import <functional>;
import <vector>;

using namespace std;

export void test()
{
	// proviamo un po' di lambda
	{
		auto f1 = [](int x) { return x + 1; };	// da int a int
		auto f2 = [](auto x) { return x + 1; };	// con auto sul lambda parametro
		auto f3 = [](auto x) -> int { return x + 1; };	// con annotazione esplicita del tipo di ritorno ed auto nel lambda parametro
		auto f4 = [](int x) -> int { return x + 1; };	// con annotazione esplicita sia del tipo del lambda parametro che del tipo di ritorno
	}

	// altri esempi con reference
	{
		auto f1 = [](const int& x) { return x + 1; };	// con un const int& come lambda parametro
		auto f2 = [](const auto& x) { return x + 1; };	// auto può essere usato insieme a const e reference: il compilatore non inferisce mai & e const con auto, inferisce solo il tipo principale
		auto f3 = [](auto& x) { x++; };					// come reference non-const
	}

	// esempi di capture: si chiamano capture le variabili catturate dalla chiusura della lambda
	// c++ permette di customizzare il comportamento delle capture in maniera molto fine
	{
		int k = 5;
		vector<int> v{ 1, 2, 3, 4, 5 };
		auto f1 = [=](int x) { return x + v[0] + k; };	// v e k sono catturate per COPIA nella chiusura della lambda
		auto f2 = [&](int x) { return x + v[0] + k; };	// v e k sono catturate per REFERENCE nella chiusura della lambda
		auto f3 = [=, &v](int x) { return x + v[0] + k; };	// tutto per copia (cioè solo k, nel nostro caso) eccetto v per reference
		auto f4 = [&, k](int x) { return x + v[0] + k; };	// tutto per reference (cioè solo v, nel nostro caso) eccetto k per copia
		auto f5 = [a=v, b=k](int x) { return x + a[0] + b; };	// tutto per copia con rebinding dei nomi: v si chiama a e k si chiama b
		auto f6 = [&a=v, b=k](int x) { return x + a[0] + b; };	// v si chiama a ed è per reference; k si chiama b ed è per copia
	}
}
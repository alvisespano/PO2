export module pairs;

import <string>;
import <functional>;


export
template <class A, typename B>
class pair
{
	template <class C, typename D> friend class pair;	// necessario per il copy constructor templatizzato

private:
	A first;
	B second;

public:
	// 2.a
	pair() : first(), second() {}
	pair(const A& a, const B& b) : first(a), second(b) {}
	pair(const pair<A, B>& p) : first(p.first), second(p.second) {}

	pair<A, B>& operator=(const pair<A, B>& p)
	{
		first = p.first;
		second = p.second;
		return *this;
	}

	// 2.c
	template <class C, typename D>
	pair(const pair<C, D>& p) : first(p.first), second(p.second) {}


	// 2.b
	pair<A, B> operator++(int)
	{
		pair<A, B> tmp(*this);
		first++;
		second++;
		return tmp;
	}

	pair<A, B>& operator++()
	{
		++first;
		++second;
		return *this;
	}

	bool operator==(const pair<A, B>& p) const
	{
		return first == p.first && second == p.second;
	}

	bool operator!=(const pair<A, B>& p) const
	{
		return !(*this == p);
	}

	// altri operatori aritmentici ed in-place sono analoghi
	pair<A, B> operator+(const pair<A, B>& p) const
	{
		return pair<A, B>(first + p.first, second + p.second);
	}

	pair<A, B>& operator+=(const pair<A, B>& p)
	{
		first += p.first;
		second += p.second;
		return *this;
	}

	const A& fst() const
	{
		return first;
	}

	A& fst()
	{
		return first;
	}

	const B& snd() const
	{
		return second;
	}

	B& snd()
	{
		return second;
	}

};


export int main()
{
	pair<int, int> p1(4, 5);
	pair<int, int> p2(p1);

	pair<std::string, bool> p3("ciao", true);
	pair<double, double> p4(p1);

	p1 = p2;

	int n = p1.fst();
	p1.snd() = p1.snd() * 3;
	p4 += p1;	// converte implicitamente il RV in un pair<double, double> tramite un conversion copy-constructor templatizzato

	return 0;
}





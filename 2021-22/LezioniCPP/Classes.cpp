
#include <string>

using namespace std;

template <typename A, typename B>
class pair
{
private:
	A first;
	B second;

public:
	pair(const A& a, const B& b) : first(a), second(b)
	{}

	pair(const pair<A, B>& p) : first(p.first), second(p.second)
	{}

	template <typename C, typename D>
	pair(const pair<C, D>& p) : first(p.first), second(p.second)
	{}

	pair<A, B>& operator=(const pair<A, B>& p)
	{
		first = p.first;
		second = p.second;
		return *this;
	}

	pair<A, B> operator++()
	{
		pair<A, B> tmp(*this);
		first++;
		second++;
		return tmp;
	}

	pair<A, B>& operator++(int)
	{
		++first;
		++second;
		return *this;
	}

	pair<A, B> operator+(const pair<A, B>& a) const
	{
		return pair<A, B>(first + a.first, second + a.second);
	}


	const A& first() const
	{
		return first;
	}

	A& first()
	{
		return first;
	}

	const B& second() const
	{
		return second;
	}

	B& second()
	{
		return second;
	}

};

int main()
{
	int x;
	int y(3);
	x = y;

	pair<int, int> p1 = { 4, 5 };
	pair<int, int> p2(p1);

	pair<string, bool> p3("ciao", true);
	pair<int, int> p4(p3);

	p1 = p2;

	int n = p1.first();
	p1.first() = 8;

	return 0;
}




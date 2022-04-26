
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

	const A& get_first() const
	{
		return first;
	}

	B get_second() const
	{
		return second;
	}

};

int main()
{
	int x;
	int y(3);
	x = y;

	pair<int, int> p1(4, 5);
	pair<int, int> p2(p1);

	pair<string, bool> p3("ciao", true);
	pair<int, int> p4(p3);

	const int& u(p4.get_first());

	return 0;
}





export module member_types;

import <iostream>;
import <vector>;
import <list>;
import <string>;

using namespace std;

class miononno
{
private:
	int x;

public:
	int& operator*() { return x; }
	
	miononno& operator++()
	{
		x++;
		return *this;
	}
};


template <class Container>
void increment_all(Container& v)
{
	for (Container::iterator it = v.begin(); it != v.end(); ++it)
	{
		*it = *it + 2;
	}
}


void test_vector_iter()
{

	vector<int> v{ 1, 2, 3, 4 };

	increment_all(v);

	list<string> l{ 1, 2, 3, 5, 6 };
	increment_all(l);





	for (size_t i = 0; i < v.size(); ++i)
	{
		int n = v[i];
		// ...
	}

	for (vector<int>::iterator it = v.begin(); it != v.end(); ++it)
	{
		++* it;		
	}

	for (vector<int>::const_iterator it = v.begin(); it != v.end(); ++it)
	{
		cout << *it << "\n";
	}


}
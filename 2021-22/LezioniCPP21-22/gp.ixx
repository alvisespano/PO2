export module gp;

import <string>;
import <vector>;

template <class T>
T sum2(T a, T b) {
	return a + b;
}

template <class C>
typename C::value_type sum(const C& v)
{
	if (v.size() > 0)
	{
		typename C::value_type r(v[0]);
		for (size_t i = 0; i < v.size(); ++i)
		{
			typename C::reference x(v[i]);
			r += x;
		}
		return r;
	}
	return C::value_type();
}


template <class InputIterator>
typename std::iterator_traits<InputIterator>::value_type
sum(InputIterator a, InputIterator b)
{
	typename std::iterator_traits<InputIterator>::value_type r;
	while (a != b)
	{
		r += *a++;
	}
	return r;
}

template <class InputIterator>
auto sum(InputIterator a, InputIterator b) -> decltype(*a)
{
	auto r;
	while (a != b)
	{
		r += *a++;
	}
	return r;
}






int main()
{
	std::vector<int> v1{ 1, 2, 3 };
	int n = sum(v1.begin(), v1.end());

	char a[10];
	int m = sum(a, a + 10);

	int x = sum(v1);
	std::vector<int> v2{ 234, 456,4567, 7 };
	int y = sum(v2);
	std::vector<double> v3{ 0.0 };
	int y = sum(v3);
}


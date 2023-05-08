export module gp;

import <string>;
import <vector>;

template <class T>
T sum2(T a, T b) {
	return a + b;
}

template <class C>
C::value_type sum(const C& v)
{
	if (v.size() > 0)
	{
		C::value_type r(v[0]);
		for (size_t i = 0; i < v.size(); ++i)
		{
			C::reference x(v[i]);
			r += x;
		}
		return r;
	}
	return C::value_type();

}

int main()
{
	std::vector<int> v1{ 1, 2, 3 };
	int x = sum(v1);

}


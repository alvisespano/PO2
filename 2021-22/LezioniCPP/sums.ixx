export module sums;

import <iterator>;
import <vector>;
import <algorithm>;
import <format>;
import <type_traits>;
import <iostream>;

using namespace std;

export namespace sums
{
	template <typename InputIterator, typename BinaryFunction>
	iterator_traits<InputIterator>::value_type sum(InputIterator first, InputIterator last, BinaryFunction f)
	{
		typename iterator_traits<InputIterator>::value_type z = *first++;
		for (; first != last; ++first)
		{
			z = f(z, *first);
		}
		return z;
	}

	template <typename InputIterator>
	auto sum(InputIterator first, InputIterator last) -> decltype(*first)
	{
		decltype(*first) z = *first++;
		for (; first != last; ++first)
		{
			z += *first;

		}
		return z;
	}

	export void test()
	{
		vector<int> v = { 1, 2, 3, 4 };
		for_each(begin(v), end(v), [](const auto& x) { cout << x << ", "; });

		cout << "sum1 = " << sum(v.begin(), v.end()) << endl;
		cout << "sum2 = " << sum(v.begin(), v.end(), [](const auto& a, const auto& b) { return a * b; }) << endl;
	}

}
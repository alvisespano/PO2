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
	template <class InputIterator, typename BinaryFunction>
	iterator_traits<InputIterator>::value_type sum(InputIterator first, InputIterator last, BinaryFunction f)
	{
		typename iterator_traits<InputIterator>::value_type z = *first++;
		for (; first != last; ++first)
		{
			z = f(z, *first);
		}
		return z;
	}


	template <class InputIterator>
	auto sum(InputIterator first, InputIterator last) -> remove_reference<decltype(* first)>::type
	{
		auto z = *first++;
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
		cout << endl;

		double arr[5] = { 1.1, 2.2, 3.3, 4.4 };


		cout << "sum vector = " << sum(v.begin(), v.end()) << endl;
		cout << "sum vector = " << sum(v.begin(), v.end()) << endl;
		cout << "sum array = " << sum(arr, arr + size(arr)) << endl;
		cout << "sum con lambda = " << sum(v.begin(), v.end(), [](const auto& a, const auto& b) { return a + b; }) << endl;
	}

}
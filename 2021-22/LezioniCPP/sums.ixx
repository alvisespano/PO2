export module sums;

import <iterator>;
import <vector>;
import <algorithm>;
import <format>;
import <type_traits>;
import <iostream>;
import <iterator>;


template <class T>
struct my_remove_reference;

template <class T>
struct my_remove_reference<T&>
{
	using type = T;
};

template <class T>
struct my_remove_reference<T&&>
{
	using type = T;
};

template <class T>
using my_remove_reference_t = typename my_remove_reference<T>::type;



export namespace sums
{
	template <class InputIterator, typename BinaryFunction>
	std::iterator_traits<InputIterator>::value_type sum(InputIterator first, InputIterator last, BinaryFunction f)
	{
		typename std::iterator_traits<InputIterator>::value_type z = *first++;
		while (first != last)
		{
			z = f(z, *first++);
		}
		return z;
	}


	template <class InputIterator>
	auto sum(InputIterator first, InputIterator last) -> my_remove_reference_t<decltype(*first)>
	{
		static_assert(std::is_same_v<my_remove_reference_t<decltype(*first)>, std::remove_reference_t<decltype(*first)>>);
		auto z = *first++;
		static_assert(std::is_same_v<my_remove_reference_t<decltype(*first)>, decltype(z)>);
		while (first != last)
		{
			z += *first++;
		}
		return z;
	}


	export void test()
	{
		std::vector<int> v = { 1, 2, 3, 4 };
		std::for_each(std::begin(v), std::end(v), [](const auto& x) { std::cout << x << ", "; });
		std::cout << std::endl;

		double arr[5] = { 1.1, 2.2, 3.3, 4.4 };

		std::cout << "sum vector = " << sum(v.begin(), v.end()) << std::endl;
		std::cout << "sum array = " << sum(arr, arr + std::size(arr)) << std::endl;
		std::cout << "sum con lambda = " << sum(v.begin(), v.end(), [](const auto& a, const auto& b) { return a + b; }) << std::endl;
	}

}
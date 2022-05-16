export module sums;

import <iterator>;
import <vector>;
import <algorithm>;
import <format>;
import <type_traits>;
import <iostream>;
import <iterator>;
import <concepts>;


export namespace sums
{
	// sum con operazione di somma binaria passata come argomento
	template <class InputIterator, class BinaryFunction>
	std::iterator_traits<InputIterator>::value_type sum(InputIterator first, InputIterator last, BinaryFunction f)
	{
		typename std::iterator_traits<InputIterator>::value_type z = *first++;
		while (first != last)
		{
			z = f(z, *first++);
		}
		return z;
	}

	// sum che richiede l'operatore += implementata tramite template classici
	template <class InputIterator>
	auto sum(InputIterator first, InputIterator last) -> std::remove_reference_t<decltype(*first)>
	{
		auto z = *first++;
		static_assert(std::is_same_v<std::remove_reference_t<decltype(*first)>, decltype(z)>);
		while (first != last)
		{
			z += *first++;
		}
		return z;
	}

	// sum con concept
	//

	template <class T>
	concept MyAddable = requires (T x, T y) { x += y; };

	template <std::input_iterator I>
		requires MyAddable<typename std::iterator_traits<I>::value_type>
	auto sum_with_concepts(I first, I last)
	{
		return sum(first, last);
	}


	export void test()
	{
		std::vector<int> v = { 1, 2, 3, 4 };
		std::for_each(std::begin(v), std::end(v), [](const auto& x) { std::cout << x << ", "; });
		std::cout << std::endl;

		double arr[5] = { 1.1, 2.2, 3.3, 4.4 };

		std::cout << "sum con lambda = " << sum(v.begin(), v.end(), [](const auto& a, const auto& b) { return a + b; }) << std::endl;

		std::cout << "sum vector = " << sum(v.begin(), v.end()) << std::endl;
		std::cout << "sum array = " << sum(arr, arr + std::size(arr)) << std::endl;

		std::cout << "sum_with_concepts vector = " << sum_with_concepts(v.begin(), v.end()) << std::endl;
		std::cout << "sum_with_concepts array = " << sum_with_concepts(arr, arr + std::size(arr)) << std::endl;
	}

}
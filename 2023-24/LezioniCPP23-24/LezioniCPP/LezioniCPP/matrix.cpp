#include <vector>
#include <iostream>

using namespace std;

template <class T>
class matrix
{
private:
	size_t cols;
	vector<T> v;

public:
	matrix() : v() {}
	matrix(const matrix<T>& m) : cols(m.cols), v(m.v) {}
	
	template <class S>
	matrix(const matrix<S>& m) : cols(m.cols), v(m.get_rows() * m.get_cols()) 
	{
		for (int i = 0; i < v.size(); ++i)
			v[i] = m.v[i];
	}

	matrix(size_t rows, size_t _cols, const T& x = T())
		: cols(_cols), v(rows* cols, x) {}

	
	explicit matrix(size_t dim) : matrix(dim, dim) {}

	operator const vector<T>&() const
	{
		return v;
	}

	matrix<T>& operator=(const matrix<T>& m)
	{
		cols = m.cols;
		v = m.v;
		return *this;
	}


	const T& operator()(size_t i, size_t j) const
	{
		return v[i * cols + j];
	}
	T& operator()(size_t i, size_t j)
	{
		return v[i * cols + j];
	}

	size_t get_cols() const { return cols; }
	size_t get_rows() const { return v.size() / cols; }
}; 

template <class C>
ostream& operator<<(ostream& os, const C& m)
{
	for (typename C::iterator it = m.v.begin(); it != m.v.end(); ++it)
	{
		typename C::value_type x = *it;
		os << x << " ";
	}
	return os;
}



void main() 
{
	matrix<int> m1(20, 30);
	matrix<double> m2(m1);

	cout << 7;

}



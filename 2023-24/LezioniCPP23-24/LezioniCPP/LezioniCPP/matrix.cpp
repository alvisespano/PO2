#include <vector>

using namespace std;

template <class T>
class matrix
{
private:
	size_t cols;
	vector<T> v;

public:
	matrix() : v() {}
	matrix(const matrix<T>& m) : v(m.v) {}
	
	
//	matrix(size_t rows, size_t _cols) : cols(_cols), v(rows* cols) {}
	matrix(size_t rows, size_t _cols, const T& x = T())
		: cols(_cols), v(rows* cols, x) {}

	
	explicit matrix(size_t dim) : matrix(dim, dim) {}

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


void main() 
{
	matrix<int> m(20, 30);
	m(8, 10) = m(3, 4);

	matrix<int> m2(40, 50);
	m = m2;
	m.operator=(m2);

	m = m2 = m; //	m.operator=(m2.operator=(m));

	matrix<int> m3;
	m3 = m;
	matrix<int> m4(m3);

}



#include <vector>

using namespace std;

template <class T>
class matrix
{
private:
	vector<T> v;

public:
	matrix() : v() {}
	matrix(const matrix<T>& m) : v(m.v) {}
	matrix(size_t rows, size_t cols) : v(rows * cols) {}
	explicit matrix(size_t dim) : matrix(dim, dim) {}

	const T& at(size_t i, size_t j) const
	{
		return v[i * cols + j];
	}
};


void main() {

	int x;
	x = 7;
	int y = 8;
	x = y;

	matrix<double> a;
	a = matrix<double>();
	matrix<double> b(...);
	b = a;
}
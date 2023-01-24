
#include <functional>
#include <iostream>
#include <utility>
#include <algorithm>

using namespace std;


using real = double;
using unary_fun = function<real(const real&)>;

#define RESOLUTION (10)


class curve
{

private:
	real a, b;
	unary_fun f;

public:
	curve(const real& a_, const real& b_, const unary_fun& f_) : f(f_), a(a_), b(b_) {}

	real get_dx() const { return (b - a) / RESOLUTION; }

	pair<real, real> interval() const { return pair<real, real>(a, b); }

	curve derivative() const
	{
		return curve(a, b, [=](const real& x) {
			real dx = get_dx(), dy = f(x + dx) - f(x);
			return dy / dx;
			});
	}

	curve primitive() const
	{
		return curve(a, b, [=](const real& x) {
			real dx = get_dx(), y = f(x);
			return y * dx;
			});
	}

	real integral() const
	{
		real dx = get_dx();
		real r = 0.;
		const unary_fun& F = primitive();
		for (real x = a; x <= b; x += dx)
		{
			r += F(x);
		}
		return r;
	}

	real operator()(const real& x) const
	{
		return f(x);
	}

	class iterator
	{
	private:
		const curve& c;
		real x;

	public:
		iterator(const curve& c_, const real& x_) : c(c_), x(x_) {}

		pair<real, real> operator*() const
		{
			return pair<real, real>(x, c.f(x));
		}

		iterator& operator++()	// il pre-incremento modifica sè stesso e non ritorna una copia
		{
			x += c.get_dx();
			return *this;
		}

		iterator operator++(int)	// il post-incremento fa una copia, modifica sé stesso e ritorna la copia
		{
			auto r(*this);
			x += c.get_dx();
			return r;
		}

		bool operator!=(const iterator& it) const
		{
			return fabs(x - it.x) > c.get_dx();	// non si confrontano mai i float direttamente con l'operatore di uguaglianza o disuguaglianza
		}
	};

	iterator begin() const
	{
		return iterator(*this, a);
	}

	iterator end() const
	{
		return iterator(*this, b + get_dx());
	}

};


ostream& operator<<(ostream& os, const curve& c)
{
	os << "dom = [" << c.interval().first << ", " << c.interval().second << "] dx = " << c.get_dx() << ": " << endl;
	/*for (const pair<real, real>& p : c)
	{
		const real& x = p.first, & y = p.second;
		os << "\tf(" << x << ") = " << y << endl;
	}*/
	for (curve::iterator it = c.begin(); it != c.end(); ++it)
	{
		const pair<real, real>& p = *it;
		auto it2 = it++;
		auto it3 = ++it;
		const real& x = p.first, & y = p.second;
		os << "\tf(" << x << ") = " << y << endl;
	}
	return os << endl;
}

int main()
{
	curve f(-1., 1., [](const real& x) { return x * x; });

	cout << f << endl
		<< f.derivative() << endl
		<< f.primitive() << endl
		<< f.primitive().derivative() << endl
		<< f.derivative().primitive() << endl
		;
	return 0;
}
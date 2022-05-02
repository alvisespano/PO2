export module pairs;

import <string>;
import <functional>;

export namespace pairs
{

	// TODO LEZIONE: mostrare questo

	template <typename This, typename T, typename Op>
	inline This& poly_op_assign(This* this_, const T& x, const Op& op)
	{
		return *this_ = op(*this_, x);
	}

#define OP_BIN(op) \
	mypair<A, B>& operator op (const mypair<A, B>& x) const \
	{ \
		return mypair<A, B>(first op x.first, second op x.second); \
	}

#define OP_ASSIGN(op) \
	auto& operator op ## =(const auto& x) \
	{ \
		return *this = *this op x; \
	}




	template <typename A, typename B>
	class mypair
	{
		template <typename C, typename D> friend class mypair;	// necessario per il conversion copy constructor

	private:
		A first;
		B second;

		template <typename Op>
		mypair<A, B> op_bin(const mypair<A, B>& x, const Op& op) const
		{
			return mypair<A, B>(op(this->first, x.first), op(this->second, x.second));
		}

		template <typename Op>
		mypair<A, B>& op_assign(const mypair<A, B>& x, const Op& op)
		{
			return poly_op_assign(this, x, op);
		}

	public:
		// default constructor
		mypair() : first(), second() {}

		// main constructor
		mypair(const A& a, const B& b) : first(a), second(b)
		{}

		// copy constructor
		mypair(const mypair<A, B>& p) : first(p.first), second(p.second)
		{}

		// conversion copy constructor
		template <typename C, typename D>
		mypair(const mypair<C, D>& p) : first(p.first), second(p.second)
		{}

		// assignment operator
		mypair<A, B>& operator=(const mypair<A, B>& p)
		{
			first = p.first;
			second = p.second;
			return *this;
		}

		// altri operatori

		mypair<A, B> operator++()
		{
			mypair<A, B> tmp(*this);
			first++;
			second++;
			return tmp;
		}

		mypair<A, B>& operator++(int)
		{
			++first;
			++second;
			return *this;
		}

		mypair<A, B> operator+(const mypair<A, B>& p) const
		{
			return mypair<A, B>(first + p.first, second + p.second);
		}

		mypair<A, B> operator-(const mypair<A, B>& p) const
		{
			return op_bin(p, std::minus<mypair<A, B>>());
		}

		/*OP_BIN(*)
		OP_BIN(/)*/

		mypair<A, B>& operator+=(const mypair<A, B>& p)
		{
			return op_assign(p, std::plus<mypair<A, B>>());
		}

		/*OP_ASSIGN(-)
		OP_ASSIGN(*)
		OP_ASSIGN(/)*/

		// TODO STUDENTI: implementare altri operatori aritmetici che hanno senso

		// metodi di accesso read/write ai campi

		const A& fst() const
		{
			return first;
		}

		A& fst()
		{
			return first;
		}

		const B& snd() const
		{
			return second;
		}

		B& snd()
		{
			return second;
		}

	};


	using std::string;

	export void test()
	{
		mypair<int, int> p1(4, 5);
		mypair<int, int> p2(p1);

		mypair<string, bool> p3("ciao", true);
		mypair<double, double> p4(p1);

		p1 = p2;

		int n = p1.fst();
		p1.snd() = p1.snd() * 3;
		p4 += p1;	// converte implicitamente il RV in un pair<double, double> tramite un conversion copy-constructor templatizzato

		//p4 -= p1;

	}

}



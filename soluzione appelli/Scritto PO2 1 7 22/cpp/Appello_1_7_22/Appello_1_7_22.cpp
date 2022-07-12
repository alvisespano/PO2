
// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 1/7/2022 per ciò che riguarda il quesito 3, ovvero la domanda che coinvolge C++.
// I quesiti 1-2 riguardanti Java sono in un progetto IntelliJ a parte, non qui.
// Il codice C++ qui esposto è standard C++14 solamente perché usa nullptr e using per i type member; tutto il resto è vanilla.

#include <iostream>
#include <vector>

using namespace std;

template <class T>
class smart_ptr
{
private:
	T* pt;
	ptrdiff_t offset;
	bool is_array;

	using counter_t = unsigned short;

	counter_t* cnt;

	void destroy()
	{
		if (cnt != nullptr && --(*cnt) == 0)
		{
			if (pt != nullptr)
			{
				if (is_array) delete pt;
				else delete[] pt;
			}
			delete cnt;
		}
	}


public:
	using value_type = T;

	smart_ptr() : pt(nullptr), offset(0), is_array(false), cnt(nullptr) {}

	explicit smart_ptr(T* pt_, bool is_array_ = false) : pt(pt_), offset(0), is_array(is_array_), cnt(new counter_t(1)) {}

	smart_ptr(const smart_ptr<T>& p) : pt(p.pt), offset(p.offset), is_array(p.is_array), cnt(p.cnt)
	{
		if (cnt != nullptr) ++(*cnt);
	}

	~smart_ptr()
	{
		destroy();
	}

	smart_ptr<T>& operator=(const smart_ptr<T>& p)
	{
		if (cnt != nullptr) ++(*p.cnt);
		destroy();
		pt = p.pt;
		cnt = p.cnt;
		return *this;
	}

	// de-reference
	T& operator*()
	{
		return *pt;
	}
	const T& operator*() const
	{
		return *pt;
	}

	// subscript
	T& operator[](size_t i)
	{
		return pt[i];
	}
	const T& operator[](size_t i) const
	{
		return pt[i];
	}

	// field access
	T* operator->()
	{
		return pt;
	}
	const T* operator->() const
	{
		return pt;
	}

	// plus
	smart_ptr<T>& operator+=(ptrdiff_t off)
	{
		offset += off;
		return *this;
	}
	smart_ptr<T> operator+(ptrdiff_t off) const
	{
		return smart_ptr<T>(*this) += off;	// ritorna il risultato di operator+=(ptrdiff_t) invocato sulla copia
	}

	// minus
	smart_ptr<T>& operator-=(ptrdiff_t off)
	{
		offset -= off;
		return *this;
	}
	smart_ptr<T> operator-(ptrdiff_t off)
	{
		return smart_ptr<T>(*this) -= off;
	}
};


int main()
{
	int* a = new int[100];
	smart_ptr<int> a1(a, true);	
	smart_ptr<int> a2(a, true);
	a1 = a2 + 10;
	a1 -= 3;

	string* sp = new string("ciao");
	smart_ptr<string> s1(sp), s2;
	s2 = s1; 

	smart_ptr<vector<double>> v1(new vector<double>(10, 1.));
	smart_ptr<vector<double>> v2(new vector<double>(20, 2.));
	v1 = v2 + 3;
	v1 -= 2;

}

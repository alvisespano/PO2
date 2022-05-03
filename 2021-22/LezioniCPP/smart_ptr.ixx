export module smart_ptr;

import <string>;

using std::string;

export
template <typename T>
class smart_ptr
{
private:
	T* pt;
	bool is_array;

//	typedef unsigned int counter_t;
	using counter_t = unsigned int;

	counter_t* cnt;

	void destroy()
	{
		if (--(*cnt) == 0)
		{
			if (is_array) delete pt;
			else delete[] pt;
			delete cnt;
		}
	}

public:
	// typedef T value_type;
	using value_type = T;

	smart_ptr(T* pt_, bool is_array_ = false) : pt(pt_), cnt(new unsigned int(1)), is_array(is_array_) {}

	smart_ptr(size_t len) : smart_ptr(new T[len], len > 1) {}

	smart_ptr(const smart_ptr<T>& p) : pt(p.pt), cnt(p.cnt), is_array(p.is_array)
	{
		++(*cnt);
	}

	~smart_ptr()
	{
		destroy();
	}

	smart_ptr<T>& operator=(const smart_ptr<T>& p)
	{
		++(*p.cnt);
		destroy();
		pt = p.pt;
		cnt = p.cnt;
		return *this;
	}

	T& operator*()
	{
		return (*this)[0];
	}

	const T& operator*() const
	{
		return *pt;
	}

	T& operator[](size_t i)
	{
		return pt[i];
	}

	const T& operator[](size_t i) const
	{
		return pt[i];
	}

	T* operator->()
	{
		return pt;
	}

	const T* operator->() const
	{
		return pt;
	}

	// TODO STUDENTI: implementare operatori +, ++, +=, -, --, -=

};

class foo
{
public:
	smart_ptr<int> pt;

	foo(smart_ptr<int> pt_) : pt(pt_) {}
};


template <typename Pointer>
void swap(Pointer& p1, Pointer& p2)
{
	typename Pointer::value_type& tmp = *p1;
	*p1 = *p2;
	*p2 = tmp;
}


void f(smart_ptr<int> p)
{
	foo oo(p);
}

void test_smart_ptr()
{
	int* a = new int[100];
	smart_ptr<int> a2(new int[100], true);
	smart_ptr<double> x(600);

	string* s = new string("ciao");
	smart_ptr<string> s2 = new string("ciao");

	s2 = s2;

	f(a2);
	// ...

	delete[] a;
	delete s;
}




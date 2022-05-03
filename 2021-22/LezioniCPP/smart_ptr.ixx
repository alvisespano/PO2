export module smart_ptr;

import <string>;

using std::string;

export
template <typename T>
class smart_ptr
{
private:
	T* pt;
	size_t len;

//	typedef unsigned int counter_t;
	using counter_t = unsigned int;

	counter_t* cnt;

	void destroy()
	{
		if (--(*cnt) == 0)
		{
			if (len == 1) delete pt;
			else delete[] pt;
			delete cnt;
		}
	}

public:
	// typedef T value_type;
	using value_type = T;

	smart_ptr(const T*& pt_, size_t len_ = 1) : pt(pt_), cnt(new int(1)), len(len_) {}

	smart_ptr(size_t len_) : pt(new T[len_]), cnt(new int(1)), len(len_) {}

	smart_ptr(const smart_ptr<T>& p) : pt(p.pt), cnt(p.cnt)
	{
		++*cnt;
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
	Pointer::value_type& tmp = *p1;
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
	smart_ptr<int> a2(new int[100], 100);
	smart_ptr<double> x(600);

	string* s = new string("ciao");
	smart_ptr<string> s2 = new string("ciao");

	s2 = s2;

	f(a2);
	// ...

	delete[] a;
	delete s;
}




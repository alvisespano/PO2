export module smart_ptr;

import <cstddef>;

template <class T>
class smart_ptr
{
private:
	T* pt;
	ptrdiff_t offset;
	size_t* cnt;
	bool is_array;

	void dec()
	{
		--(*cnt);
		if (*cnt == 0)
		{
			if (is_array) delete[] pt;
			else delete pt;
			delete cnt;
		}
	}

	void inc()
	{
		++(*cnt);
	}

	smart_ptr(T* pt_, ptrdiff_t offset_, size_t* cnt_, bool is_array_)
		: pt(pt_), offset(offset_), cnt(cnt_), is_array(is_array_) {
		inc();
	}

public:
	smart_ptr(T* p, bool is_array_) 
		: pt(p), offset(0), cnt(new size_t(1)), is_array(is_array_) {}

	explicit smart_ptr(T* p) : smart_ptr<T>(p, false) {}

	smart_ptr(const smart_ptr<T>& p) 
		: pt(p.pt), cnt(p.cnt), offset(p.offset)
	{
		inc();
	}

	~smart_ptr()
	{
		dec();
	}

	smart_ptr<T>& operator=(const smart_ptr<T>& p)
	{
		if (pt != p.pt)
		{
			dec();
			pt = p.pt;
			cnt = p.cnt;
			offset = p.offset;
			is_array = p.is_array;
			inc();
		}
		return *this;
	}

	T& operator*()
	{
		return pt[offset];
	}

	const T& operator*() const
	{
		return pt[offset];
	}

	bool operator==(const smart_ptr<T>& p) const
	{
		return pt == p.pt && offset == p.offset;
	}

	bool operator!=(const smart_ptr<T>& p) const
	{
		return !(*this == p);
	}

	operator T*()
	{
		return pt + offset;
	}

	operator const T* () const
	{
		return pt + offset;
	}

	smart_ptr<T> operator+(ptrdiff_t d) const
	{
		return smart_ptr<T>(pt, offset + d, cnt, is_array);
	}

	smart_ptr<T> operator-(ptrdiff_t d) const
	{
		return *this + (-d);
	}

	smart_ptr<T>& operator+=(ptrdiff_t d)
	{
		offset += d;
		return *this;
	}

	smart_ptr<T>& operator-=(ptrdiff_t d)
	{
		return *this += -d;
	}

	smart_ptr<T>& operator++()
	{
		return *this += 1;
	}

	smart_ptr<T> operator++(int)
	{
		smart_ptr<T> r(*this);
		++(*this);
		return r;
	}

	smart_ptr<T>& operator--()
	{
		return *this -= 1;
	}

	smart_ptr<T> operator--(int)
	{
		smart_ptr<T> r(*this);
		--(*this);
		return r;
	}

	T& operator[](size_t i)
	{
		return pt[offset + i];
	}

	const T& operator[](size_t i) const
	{
		return pt[offset + i];
	}

	T* operator->()
	{
		return pt + offset;
	}

	const T* operator->() const
	{
		return pt + offset;
	}
};



template <class Pointer>
void demo(Pointer p)
{
	Pointer p2(p);
	p = p2;
	auto x = *p;
	Pointer p3(p + 4);
	p2 = p - 2;
	++p2;
	p2++;
	--p2;
	p2--;
	p += 8;
	p -= 5;
	auto y = p[5];
}

struct S {
	int a;
	bool b;
};

export void test()
{
	int* a = new int[10];
	demo(a);

	smart_ptr<int> b(new int(23));
	demo(b);

	S* s1 = new S[10];
	int n1 = s1->a + 1;
	smart_ptr<S> s2(s1, true);
	S s3(s2[4]);
	s3 = s3;


}

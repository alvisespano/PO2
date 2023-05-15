export module smart_ptr;

import <cstddef>;

template <class T>
class smart_ptr
{
private:
	T* pt;
	ptrdiff_t offset;
	size_t* cnt;

	void dec()
	{
		--(*cnt);
		if (*cnt == 0)
		{
			delete pt;
			delete cnt;
		}
	}

public:
	smart_ptr(T* p) : pt(p), offset(0), cnt(new size_t(1)) {}

	smart_ptr(const smart_ptr<T>& p) : pt(p.pt), cnt(p.cnt), offset(p.offset)
	{
		++(*cnt);
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
			++(*p.cnt);
			this->pt = p.pt;
			this->cnt = p.cnt;
		}
		return *this;
	}

	T& operator*()
	{
		return *pt;
	}

	const T& operator*() const
	{
		return *pt;
	}

	bool operator==(const smart_ptr<T>& p)
	{
		return pt == p.pt;
	}

	bool operator!=(const smart_ptr<T>& p)
	{
		return !(*this == p);
	}

	operator T*()
	{
		return pt;
	}

	operator const T* () const
	{
		return pt;
	}

	smart_ptr<T> operator+(ptrdiff_t d)
	{
		return smart_ptr<T>(pt + d);
	}
};




class C {
private:
	smart_ptr<int> p;
public:
	C(int* p_) : p(p_) {}
	smart_ptr<int> get() const { return p; }
};


export void test()
{

	smart_ptr<int> a(new int(23));
	C c(a);
	smart_ptr<int> b = c.get();

	smart_ptr<int> d(new int(436745));
	d = a;



}
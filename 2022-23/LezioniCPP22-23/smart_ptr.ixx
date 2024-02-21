export module smart_ptr;

import <cstddef>;
import <cassert>;
import <type_traits>;

template <class Ty, size_t Len = (std::is_array_v<Ty> ? std::extent_v<Ty, 0> : 1)>
class smart_ptr
{
private:
	using T = std::remove_extent_t<Ty>;

	T* pt;
	ptrdiff_t offset;
	size_t* cnt;

	using self = smart_ptr<T, Len>;

	void dec()
	{
		--(*cnt);
		if (*cnt == 0)
		{
			if constexpr (Len >= 1) delete[] pt;
			else delete pt;
			delete cnt;
		}
	}

	void inc()
	{
		++(*cnt);
	}

	smart_ptr(T* pt_, ptrdiff_t offset_, size_t* cnt_)
		: pt(pt_), offset(offset_), cnt(cnt_) {
		inc();
	}

public:
	explicit smart_ptr(T* p) 
		: pt(p), offset(0), cnt(new size_t(1)) {}

	smart_ptr(const self& p) 
		: pt(p.pt), cnt(p.cnt), offset(p.offset)
	{
		inc();
	}

	~smart_ptr()
	{
		dec();
	}

	self& operator=(const self& p)
	{
		if (pt != p.pt)
		{
			dec();
			pt = p.pt;
			cnt = p.cnt;
			offset = p.offset;
			inc();
		}
		return *this;
	}

	T& operator*()
	{
		return const_cast<T&>(*std::as_const(*this));
	}

	const T& operator*() const
	{
		return pt[offset];
	}

	bool operator==(const self& p) const
	{
		return pt == p.pt && offset == p.offset;
	}

	bool operator!=(const self& p) const
	{
		return !(*this == p);
	}

	operator T*()
	{
		return const_cast<T*>(std::as_const(*this).operator const T*());
	}

	operator const T*() const
	{
		return pt + offset;
	}

	self operator+(ptrdiff_t d) const
	{
		return smart_ptr<T>(pt, offset + d, cnt, is_array);
	}

	self operator-(ptrdiff_t d) const
	{
		return *this + (-d);
	}

	self& operator+=(ptrdiff_t d)
	{
		offset += d;
		return *this;
	}

	self& operator-=(ptrdiff_t d)
	{
		return *this += -d;
	}

	self& operator++()
	{
		return *this += 1;
	}

	self operator++(int)
	{
		self r(*this);
		++(*this);
		return r;
	}

	self& operator--()
	{
		return *this -= 1;
	}

	self operator--(int)
	{
		self r(*this);
		--(*this);
		return r;
	}

	T& operator[](int i)
	{
		return const_cast<T&>(std::as_const(*this)[i]);
	}

	const T& operator[](int i) const
	{
		assert(offset + i < Len && offset + i >= 0);
		return pt[offset + i];
	}

	T* operator->()
	{
		return const_cast<T*>(std::as_const(*this).operator->());
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

	smart_ptr<int> a(new int(1));

	smart_ptr<int[]> a(new int[20]);

}

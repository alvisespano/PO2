

template <class T>
class smart_ptr
{
private:
	T* pt;
	const T* orig;
	unsigned int* cnt;

public:
	explicit smart_ptr(T* _pt) : pt(_pt), orig(_pt), cnt(new unsigned int(1u)) {}

	smart_ptr(const smart_ptr<T>& sp) : pt(sp.pt), orig(sp.orig), cnt(sp.cnt) 
	{
		++ *cnt;
	}

	~smart_ptr()
	{
		if (-- *cnt == 0)
		{
			delete orig;
			delete cnt;
		}
	}

	T& operator*()
	{
		return *pt;
	}

	const T& operator*() const
	{
		return *pt;
	}

	smart_ptr<T>& operator++()
	{
		++pt;
		return *this;
	}

	smart_ptr<T> operator++(int)
	{
		smart_ptr<T> r(*this);
		++pt;
		return r;
	}
};



void main()
{
	int n = 7;
	int m = n++ + 1;

}
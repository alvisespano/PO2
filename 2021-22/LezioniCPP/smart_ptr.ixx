export module smart_ptr;

export
template <typename T>
class smart_ptr
{
private:
	T* pt;

//	typedef unsigned int counter_t;
	using counter_t = unsigned int;

	counter_t* cnt;

public:
	smart_ptr(const T*& pt_) : pt(pt_), cnt(new int(0)) {}

	smart_ptr(const smart_ptr<T>& p) : pt(p.pt), cnt(p.cnt)
	{
		++(*cnt);
	}

	~smart_ptr()
	{
		if (--(*cnt) == 0)
		{
			delete pt;
			delete cnt;
		}
	}


};
export module smart_ptr;

import <cstddef>;
import <cassert>;
import <utility>;
import <type_traits>;


namespace smart_ptr
{

	// definiamo un piccolo type trait che ci dice se possiamo usare la delete[] per un certo tipo
	template <class T, class = void>
	struct can_delete
	{
		static constexpr bool value = false;
	};

	// questa � una specializzazione parziale di can_delete che il compilatore sceglie solamente se compila il decltype nel secondo template argument
	template <class T>
	struct can_delete<T, std::void_t<decltype(delete[] declval<T*>())>>
	{
		static constexpr bool value = true;
	};

	// uno smart points ha un tipo a cui punta ed una dimensiona statica templatizzata, il cui valore di default 1 se un non-array altrimenti la lunghezza del suo extent
	// NOTA: si chiama extent la lunghezza statica degli array, ad esempio la parte tra parentesi quadre in: int[10]
	export
	template <class Ty, size_t L = (std::is_array_v<Ty> ? std::extent_v<Ty> : 1)>
	class smart_ptr
	{
	private:
		using T = std::remove_extent_t<Ty>;
		using self = smart_ptr<Ty, L>;

	protected:
		T* pt;
		ptrdiff_t offset;
		size_t* cnt;

		void dec()
		{
			--(*cnt);
			if (*cnt == 0)
			{
				if constexpr (can_delete<T>::value) delete[] pt;
				else delete pt;
				delete cnt;
			}
		}

		void inc()
		{
			++(*cnt);
		}


	public:
		smart_ptr(T* pt_, ptrdiff_t offset_, size_t* cnt_)
			: pt(pt_), offset(offset_), cnt(cnt_)
		{
			assert(offset >= 0 && offset < L);
			inc();
		}

		explicit smart_ptr(T* p) : smart_ptr(p, 0, new size_t(1)) {}

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

		operator T* ()
		{
			return const_cast<T*>(std::as_const(*this).operator const T * ());
		}

		operator const T* () const
		{
			return pt + offset;
		}

		self operator+(ptrdiff_t d) const
		{
			return self(pt, offset + d, cnt);
		}

		self operator-(ptrdiff_t d) const
		{
			return *this + (-d);
		}

		self& operator+=(ptrdiff_t d)
		{
			assert(offset + d >= 0 && offset + d < L);
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

		T* operator->()
		{
			return const_cast<T*>(std::as_const(*this).operator->());
		}

		const T* operator->() const
		{
			return pt + offset;
		}

		T& operator[](ptrdiff_t i)
		{
			return const_cast<T&>(std::as_const(*this)[i]);
		}

		const T& operator[](ptrdiff_t i) const
		{
			assert(offset + i >= 0 && offset + i < L);
			return pt[offset + i];
		}
	};



	// usa tutti gli operatori utilizzabili per i pointer classici
	// � templatizzata cos� � possibile utilizzarla con tipi qualunque, basta che implementino gli operatori richiesti
	// la usiamo per testare gli smart pointer
	template <class Pointer>
	void demo(Pointer p)
	{
		Pointer p2(p);
		p = p2;
		*p;		// in C, C++ ed altri linguaggi imperativi non � necessario utilizzare il risultato di una chiamata a funzione o operatore
		const Pointer p3(p + 2);
		p2 = p + 2;
		++p2;
		p++;
		--p2;
		p += 1;
		p -= 2;
		p3[0];
	}


	export void test()
	{
		int* a = new int[10];
		demo(a);

		smart_ptr<int[5]> a2(new int[5]);
		demo(a2);

		smart_ptr<int, 10> b(a);
		demo(b);

		smart_ptr<double[10]> d(new double[10]);
		demo(d);

	}

}


export module zoo;

import <string>;

export namespace zoo
{



	export class animal
	{
	protected:
		int weight_;

	public:
		explicit animal(int w) : weight_(w) {}
		virtual ~animal() {}

		animal(const animal& a) : weight_(a.weight_) {}

		animal& operator=(const animal& a)
		{
			weight_ = a.weight_;
			return *this;
		}

		// void eat(const animal* a, animal* this)
		void eat(const animal* a) 
		{
			weight_ += a->weight_;
		}

		const int& weight() const { return weight_; }
		int& weight() { return weight_; }
	};

	class dog : public animal
	{
	private:
		int* a;

	public:
		explicit dog(int w) : animal(w), a(new int[10]) {}

		~dog() override
		{
			delete[] a;
		}

		// void eat(const animal* a, dog* this)
		void eat(const animal* a)
		{
			weight() = a->weight() * 2;
		}
	};

	class cat : public animal
	{
	public:
		explicit cat(int w) : animal(w) {}

		void eat(const animal* a) 
		{
			weight() = a->weight() / 3;
		}
	};

	class labrador : public dog
	{
	public:
		explicit labrador(int w) : dog(w) {}

		void eat(const animal* a) override
		{
			weight() = a->weight() * 5;
		}
	};


	void f()
	{
		{
			animal a(60);
			dog b(10);
			a = b;
			animal c = b;
			c.eat(&c);
		}

		{
			animal* a = new animal(60);
			dog* b = new dog(10);
			a = b;
			animal* c = b;
			c->eat(c);

			delete a;
			delete c;
		}

		{
			animal a(60);
			dog b(10);
			animal& c = b;
			c.eat(&c);
		}
	}


	void self_cannibal(animal* a)
	{
		a->eat(a);
	}

	template <class A>
	void self_cannibal2(A* a)
	{
		a->eat(a);
	}

	export void test()
	{	
		animal fido(50);
		animal* pluto = new dog(40);

		fido.eat(pluto);
		pluto->eat(&fido);

		animal pluto2(labrador(40));
		pluto2.eat(pluto);

		delete pluto;
	}

}



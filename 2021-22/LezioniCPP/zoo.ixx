
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

		virtual void eat(const animal* a)
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

		void eat(const animal* a) override
		{
			weight() = a->weight() * 2;
		}
	};

	class cat : public animal
	{
	public:
		explicit cat(int w) : animal(w) {}

		void eat(const animal* a) override
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

	export void test()
	{
		animal fido(50);
		animal* pluto = new dog(40);

		fido.eat(pluto);
		pluto->eat(&fido);

		animal pluto2(dog(40));
		pluto2.eat(pluto);

		delete pluto;
	}



}



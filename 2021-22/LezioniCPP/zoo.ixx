
export module zoo;

namespace zoo
{

	class animal
	{
	protected:
		int weight_;

	public:
		explicit animal(int w) : weight_(w) {}

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
	public:
		explicit dog(int w) : animal(w) {}

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
			weight() = a->weight() * 2;
		}
	};

	export void test()
	{
		animal fido(50);
		animal* pluto = new dog(40);

		fido.eat(pluto);
		pluto->eat(&fido);

	}
}



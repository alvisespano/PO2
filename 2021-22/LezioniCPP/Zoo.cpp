
namespace zoo
{

	class animal
	{
	protected:
		int weight;

	public:
		explicit animal(int w) : weight(w) {}

		animal(const animal& a) : weight(a.weight) {}

		virtual void eat(const animal& a)
		{
			weight += a.weight;
		}
	};

	class dog : public animal
	{
	public:
		void eat(const animal& a) override
		{
			weight += a.weight * 2;
		}
	};


	int main()
	{
		animal fido(50);
		animal* pluto = new animal(40);

		fido.eat(*pluto);

		return 0;
	}
}



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
		int* a;	// supponiamo che un dog abbia un array al suo interno (non lo usiamo davvero, è solo un esempio per mostrare una new ed una delete)

	public:
		explicit dog(int w) : animal(w), a(new int[10]) {}	// l'array lo inizializziamo con una new[]

		~dog() override
		{
			delete[] a;	// è necessario distruggere l'array con una delete[]
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
		animal fido(50);	// questo oggetto è nello stack
		animal* pluto = new dog(40);	// questo invece è nello heap, inoltre c'è subsumption qui

		fido.eat(pluto);	// invoca la eat() di animal
		pluto->eat(&fido);	// invoca la eat() di dog perché c'è il dynamic dispatching in azione, grazie al fatto che eat() è virtual

		animal pluto2 = labrador(40);	// questa non è una subsumption, è una invocazione del copy-constructor
		pluto2.eat(pluto);	// invoca la eat() di animal semplicemente perché l'oggetto è davvero un animal copiato da un labrador: nessun dynamic dispatching ha luogo

		delete pluto;	// distruggi pluto chiamando il distruttore di dog: anche il distruttore è virtual, quindi è in virtual table, quindi è soggetto a dynamic dispatching pure lui
	}

}




#include <iostream>



class animal
{
protected:
	int weight;
	double speed;

public:
	animal() : weight(), speed() {}
	animal(int w, double sp) : weight(w), speed(sp) {}
	animal(const animal& a) : weight(a.weight), speed(a.speed) {}

	const int& weight() const { return weight; }
	
	int& weight() { return weight; }

	virtual void eat(const animal& a)
	{
		weight += a.weight;
	}
};

class dog : public animal
{
private:
	bool has_pedigree;

public:
	dog(int w, double sp, bool ped) : animal(w, sp), has_pedigree(ped) {}

	void eat(const animal& a) {
		weight() += a.weight() / 2;
	}
};


void main() {
	animal* a1 = new animal(7, 2.34);
	animal a2(7, 2.34);

	animal a3(a2);
	animal* a4 = new animal(a2);

	a2.eat(a2);
	
	dog fido(60, 45.34, false);
	animal& a5 = fido;
	a5.eat(a2);

	dog* fufi = new dog(3, 100., true);
	animal* a6 = fufi;
	a6->eat(a2);

	animal pippo(fido);
	pippo.eat(a2);

}






int main()
{
}


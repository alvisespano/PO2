
#include <iostream>



class animal
{
private:
	int weight;
	double speed;

public:
	animal(int w, double sp) : weight(w), speed(sp) {}

	animal(const animal& a) : weight(a.weight), speed(a.speed) {}

	int get_weight()
	{
		return weight;
	}

	virtual void eat(const animal& a)
	{
		weight += a.weight;
	}
};

class dog : public animal
{

};





int main()
{
}


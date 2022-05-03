#include <iostream>

import pairs;
import sums;
import zoo;



template <typename Gigi>
int f(const Gigi& gigi)
{
	return gianni(&gigi);
}


int gianni(int x) { return x + 1;  }

int main()
{
	sums::test();
	zoo::test();
	pairs::test();

	f(3);

	return 0;
}


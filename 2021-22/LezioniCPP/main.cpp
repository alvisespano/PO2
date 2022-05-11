#include <iostream>
#include <exception>

import pairs;
import sums;
import zoo;
import iterators;

using namespace std;

int main()
{
	try
	{
		sums::test();
		return 0;
	}
	catch (exception& e)
	{
		cerr << "exception caught: " << e.what() << endl;
		return 1;
	}
}


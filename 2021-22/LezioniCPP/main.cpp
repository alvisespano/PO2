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
		test_iterators();
		return 0;
	}
	catch (exception& e)
	{
		cerr << "exception caught: " << e.what() << endl;
		return 1;
	}
}


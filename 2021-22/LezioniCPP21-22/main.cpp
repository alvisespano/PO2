#include <iostream>
#include <exception>

import pairs;
import sums;
import iterators;
import <vector>;

using namespace std;

int main()
{
	try
	{
		sums::test();	// chiamare il test che si preferisce
		return 0;
	}
	catch (exception& e)
	{
		cerr << "exception caught: " << e.what() << endl;
		return 1;
	}
}


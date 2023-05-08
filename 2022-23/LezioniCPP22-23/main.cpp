#include <iostream>
#include <exception>

import sums;
import zoo;
import macros;

using namespace std;

int main()
{
	try
	{
		sums::test();
		zoo::test();
		macros::test();
		return 0;
	}
	catch (exception& e)
	{
		cerr << "exception caught: " << e.what() << endl;
		return 1;
	}
}


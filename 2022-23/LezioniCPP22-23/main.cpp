#include <iostream>
#include <exception>

import sums;
import zoo;
import macros;
import some_lambdas;
import smart_ptr;

using namespace std;

int main()
{
	try
	{
		smart_ptr::test();
		sums::test();
		zoo::test();
		macros::test();
		some_lambdas::test();
		return 0;
	}
	catch (exception& e)
	{
		cerr << "exception caught: " << e.what() << endl;
		return 1;
	}
}


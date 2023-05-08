
#include <pizza.h>
#include <pizza.h>


#define MYMACRO ciao ragazzi sono io

#define MYMACRO2(a, b, c) (a + b + c)

#ifdef MACRO
....

#else

#endif

#pragma 


void swap_int(int* a, int* b) {
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

void swap_double(double* a, double* b) {
	double tmp = *a;
	*a = *b;
	*b = tmp;
}

#define SWAP(T) \\
void swap_T(T* a, T* b) { \\
	T tmp = *a; \\
	*a = *b; \\
	*b = tmp; \\
} 

SWAP(int)
SWAP(double)
SWAP(char)
SWAP(unsigned long)
SWAP(mio nonno)










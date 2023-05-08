export module macros;

namespace macros {
	// in linguaggio C non ci sono forme di polimorfismo: è pertanto necessario replicare il codice per tipi diversi
	// questo è molto scomodo e prono agli errori
	void myswap_int(int* a, int* b) {
		int tmp = *a;
		*a = *b;
		*b = tmp;
	}

	void myswap_double(double* a, double* b) {
		double tmp = *a;
		*a = *b;
		*b = tmp;
	}

	// usando le macro del preprocessore è possibile generare versioni diverse dello stesso codice sostituendo letteralmente l'argomento della macro
	// questa macro si chiama SWAP ed ha un parametro di nome T
	// l'operatore ## permette di appendere l'argomento della macro senza introdurre spazi
	// si badi che quando si definisce una macro che deve essere indentata dentro un blocco, la convenzione è mettere il # ad inizio riga e tabbare la define
	// il backslash immediatamente seguito dalla fine della riga, permette di definire macro su più righe
#	define SWAP(T) \
	void swap_##T(T* a, T* b) { \
		T tmp = *a; \
		*a = *b; \
		*b = tmp; \
	}

	// qui usiamo la macro più volte: essa definirà varie versioni della swap con nomi diversi: swap_int, swap_double
	SWAP(int)
	SWAP(double)
	SWAP(char)
	SWAP(long)


	export void test()
	{
		{
			int x = 3, y = 6;
			swap_int(&x, &y);
		}
		{
			long x = 3, y = 6;
			swap_long(&x, &y);
		}
	}

}

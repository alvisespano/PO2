export module pairs;

import <string>;
import <functional>;

export namespace pairs
{

	// utility generale per definire operatori aritmetici di assegnamento in-place (es: +=, -= ecc) tramite una funzione di ordine superiore
	// questa funzione non è specifica per mypair ma può definire operatori aritmetici in-place dato un operatore binario qualunque
	// in altre parole, essa non ha bisogno di sapere com'è fatta la classe su cui opera: gli è sufficiente conoscere l'operatore artimetico binario e necessita dell'esistenza dell'assegnamento
	template <typename This, typename Op>
	inline This& gen_op_assign(This* this_, const This& x, const Op& op)
	{
		return *this_ = op(*this_, x);	// richiede sia definito definito operator=()
	}


	// classe templatizzata mypair
	//

	template <typename A, typename B>
	class mypair
	{
		template <typename C, typename D> friend class mypair;	// necessario per il conversion copy constructor

	public:
		// main constructor: inizializza i campi privati first e second copiando gli argomenti a e b; ovvero invoca i copy constructor di A e B
		mypair(const A& a, const B& b) : first(a), second(b)
		{}

	private:
		A first;
		B second;

		// metodo privato di utility per definire operatori aritmetici tramite funzioni di ordine superiore
		// occorre passare i due operatori per i tipi A e B come argomento
		template <typename Op1, typename Op2>
		inline mypair<A, B> op_bin(const mypair<A, B>& x, const Op1& op_first, const Op2& op_second) const
		{
			return mypair<A, B>(op_first(first, x.first), op_second(second, x.second));
		}

		// metodo privato di utility per definire operatori aritmetici con assegnamento in-place stubbando poly_op_assign() con this come primo argomento
		template <typename Op>
		inline mypair<A, B>& op_assign(const mypair<A, B>& x, const Op& op)
		{
			return gen_op_assign(this, x, op);
		}

		// macro per definire operatori aritmetici facilmente: essa chiama a sua volta l'operatore op sui campi privati della mypair
		// questa macro fa una cosa simile a ciò che fa la funzione op_bin(), ma lo fa con una macro anziché con una funzione di ordine superiore
		// NOTA: definire una macro dentro una classe non ha un vero motivo: le macro sono entità gestite dal pre-processore e non sono soggette a scoping
		//       questa macro è definita qui solamente perché a livello logico opera con mypair
#		define OP_BIN(op) \
			mypair<A, B> operator op (const mypair<A, B>& x) const \
			{ \
				return mypair<A, B>(first op x.first, second op x.second); \
			}

		// macro per definire operatori aritmetici facilmente: essa chiama a sua volta l'operatore op tra this e l'argomento x
		// questa macro fa una cosa simile a ciò che fa global_op_assign(), ma lo fa con una macro anziché con una funzione di ordine superiore
		// è interessante notare che anche questa macro, come global_op_assign(), non menziona i campi privati
#		define OP_ASSIGN(op) \
			mypair<A, B>& operator op ## =(const mypair<A, B>& x) \
			{ \
				return *this = *this op x; \
			}


	public:
		// default constructor: chiama i default constructor dei campi privati, quindi deve esistere un default construtor per i tipi A e B
		mypair() : first(), second() {}

		

		// copy constructor: invoca i copy constructor di A e B
		mypair(const mypair<A, B>& p) : first(p.first), second(p.second)
		{}

		// conversion copy constructor: richiede l'esistenza di un costruttore di A tramite un argomento di tipo C, e di B tramite un argomento di tipo D
		template <typename C, typename D>
		mypair(const mypair<C, D>& p) : first(p.first), second(p.second)
		{}

		// assignment operator: richiede che sia definito operator=() per A e B
		mypair<A, B>& operator=(const mypair<A, B>& p)
		{
			first = p.first;
			second = p.second;
			return *this;
		}

		// post-incremento: richiede che sia definito l'operatore di post-incremento per A e B
		mypair<A, B> operator++()	// ritorna una NUOVA mypair per copia
		{
			mypair<A, B> tmp(*this);
			first++;
			second++;
			return tmp;
		}

		// pre-incremento: richiede che sia definito l'operatore di pre-incremento per A e B
		mypair<A, B>& operator++(int)	// ritorna un reference alla mypair appena incrementata
		{
			++first;
			++second;
			return *this;
		}

		// seguono gli operatori aritmetici in-place e non implementati in vari modi: direttamente, usando le funzioni di utility e con le macro
		//

		// operator+: richiede l'esistenza dell'operator+() per A e B
		mypair<A, B> operator+(const mypair<A, B>& p) const
		{
			return mypair<A, B>(first + p.first, second + p.second);
		}

		// operator-: usando op_bin() è sufficiente passare le 2 operazioni di sottrazione binaria come argomenti
		mypair<A, B> operator-(const mypair<A, B>& p) const
		{
			return op_bin(p, std::minus<A>(), std::minus<B>()); // passiamo DUE VOLTE l'operazione di sottrazione perché occorre passare quella per A e quella per B
		}

		// operator* e operator/ definiti usando la macro
		OP_BIN(*)
		OP_BIN(/)

		// operator+: usando op_assign() è sufficiente passare l'operazione di somma binaria come argomento
		mypair<A, B>& operator+=(const mypair<A, B>& p)
		{
			return op_assign(p, std::plus<mypair<A, B>>());
		}

		// operator-: richiede solamente l'esistenza dell'operator=() e dell'operator-()
		// questa implementazione non menziona i campi ed è praticamente uguale a quello che fa global_op_assign(), con la differenza che quest'ultima lo fa in modo generale per qualunque operatore binario
		mypair<A, B>& operator-=(const mypair<A, B>& p)
		{
			return *this = *this - p;
		}

		// operator* e operator/ definiti usando la macro
		OP_ASSIGN(*)
		OP_ASSIGN(/)

		// TODO STUDENTI: implementare altri operatori aritmetici che hanno senso

		// metodi di accesso read/write ai campi
		//

		const A& fst() const
		{
			return first;
		}

		A& fst()
		{
			return first;
		}

		const B& snd() const
		{
			return second;
		}

		B& snd()
		{
			return second;
		}

	};


	using std::string;

	export void test()
	{
		mypair<int, int> p1(4, 5);
		mypair<int, int> p2(p1);

		mypair<string, bool> p3("ciao", true);
		mypair<double, double> p4(p1);

		p1 = p2;

		int n = p1.fst();
		p1.snd() = p1.snd() * 3;
		p4 += p1;	// converte implicitamente il RV in un pair<double, double> tramite un conversion copy-constructor templatizzato

		p4 -= p1;

	}

}



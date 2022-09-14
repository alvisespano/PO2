
// Questo sorgente contiene le soluzioni dell'esame scritto di PO2 del 1/7/2022 per ciò che riguarda il quesito 2, ovvero l'esercizio di C++.
// Il quesito 1 riguardante Java è in un progetto IntelliJ a parte, non qui.
// Il codice qui esposto è C++14. 
// ATTENZIONE: il codice qui fornito è ricco di dettagli e complessità, allo scopo di fornire materiale di studio.

#include <iostream>
#include <iterator>
#include <cstddef>

template <class T>
class tree_node
{
public:
	T data;
	tree_node<T>* left, * right;

private:
	tree_node<T>* parent;

	static bool are_equal(const tree_node<T>* a, const tree_node<T>* b)
	{
		return a == b || (a != nullptr && b != nullptr && *a == *b);
	}

	static tree_node<T>* get_next_node(const tree_node<T>* n) {
		if (n->left != nullptr)
			return n->left;
		else if (n->right != nullptr)
			return n->right;
		else {
			while (n->parent != nullptr) {
				const tree_node<T>* last = n;
				n = n->parent;
				if (n->right != nullptr && n->right != last)
					return n->right;
			}
			return nullptr;
		}
	}

public:
	// 2.c

	tree_node() = default;
	tree_node(const tree_node<T>& t) = default;
	tree_node<T>& operator=(const tree_node<T>& t) = default;

	~tree_node()
	{
		if (left != nullptr)
		{
			delete left;
			left = nullptr;
		}
		if (right != nullptr)
		{
			delete right;
			right = nullptr;
		}
	}

	tree_node(const T& v, tree_node<T>* l, tree_node<T>* r) : data(v), left(l), right(r), parent(nullptr)
	{
		if (left != nullptr) left->parent = this;
		if (right != nullptr) right->parent = this;
	}

	// 2.b

	bool operator==(const tree_node<T>& t) const
	{
		return data == t.data && are_equal(left, t.left) && are_equal(right, t.right);
	}

	// 2.a

	// iteratore non-const
	class my_iterator
	{
		friend class my_const_iterator;	// questo serve perché altrimenti my_const_iterator non può accedere al campo current di my_iterator

	private:
		tree_node<T>* current;

	public:
		using iterator_category = std::forward_iterator_tag;	// questo member type indica che si tratta di un ForwardIterator, si veda la doc di STL per i dettagli
		using difference_type = std::ptrdiff_t;
		using value_type = T;
		using pointer = T*; 
		using reference = T&;

		my_iterator() = default;
		my_iterator(const my_iterator& i) = default;
		my_iterator& operator=(const my_iterator& i) = default;

		my_iterator(tree_node<T>* t) : current(t) {}

		reference operator*() { return current->data; }
		pointer operator->() { return &current->data; }

		my_iterator& operator++()
		{
			current = get_next_node(current);
			return *this;
		}

		my_iterator operator++(int)
		{
			my_iterator r(*this);
			current = get_next_node();
			return r;
		}

		bool operator==(const my_iterator& i) const { return current == i.current; }
		bool operator!=(const my_iterator& i) const { return !(*this == i); }

	};

	// interatore const
	// si noti come sono praticamente uguali a parte il fatto che gesticono un nodo const oppure no, con conseguente impatto in tutti i tipi di ritorno dei vari operatori
	// questa replicazione di codice sarebbe evitabile solamente tramite un complesso uso dei template, troppo complesso per questo corso
	// chi è interessato a sapere come evitare questa duplicazione di codice dovuta a const vs. non-const può approfondire qui: https://stackoverflow.com/questions/765148/how-to-remove-constness-of-const-iterator
	class my_const_iterator
	{
	private:
		const tree_node<T>* current;

	public:
		using iterator_category = std::forward_iterator_tag;
		using difference_type = std::ptrdiff_t;
		using value_type = const T;
		using pointer = const T*;
		using reference = const T&;

		my_const_iterator() = default;
		my_const_iterator(const my_const_iterator& i) = default;
		my_const_iterator& operator=(const my_const_iterator& i) = default;

		// questo costruttore è molto interessante: permette di costruire un my_const_iterator dato un my_iterator: in altre parole possiamo convertire un iteratore non-const in uno const
		// il motivo per cui è necessario è se chiamiamo begin() su un tree_node non-const ma vogliamo un const_iterator perché lo leggiamo soltanto
		my_const_iterator(const my_iterator& i) : current(i.current) {}

		my_const_iterator(const tree_node<T>* t) : current(t) {}

		reference operator*() const	{ return current->data;	}
		pointer operator->() const { return &current->data;	}

		my_const_iterator& operator++()
		{
			current = get_next_node(current);
			return *this;
		}

		my_const_iterator operator++(int)
		{
			my_const_iterator r(*this);
			current = get_next_node();
			return r;
		}

		bool operator==(const my_const_iterator& i) const {	return current == i.current; }
		bool operator!=(const my_const_iterator& i) const {	return !(*this == i); }

	};

	// definiamo questi member type perché sono quelli che i Container STL solitamente definiscono
	using const_iterator = my_const_iterator;	// rebinding della nested class my_const_iterator definita sopra
	using iterator = my_iterator;				// rebinding della nested class my_iterator definita sopra
	using value_type = T;

	const_iterator begin() const
	{
		return const_iterator(this);
	}

	const_iterator end() const
	{
		return const_iterator(nullptr);
	}

	iterator begin()
	{
		return iterator(this);
	}

	iterator end()
	{
		return iterator(nullptr);
	}

};

// 2.c
// pseudo-costruttori
// invece di fare metodi statici facciamo funzioni templatizzate globali, così il template argument è inferito e diventano più comode da usare

template <class T>
tree_node<T>* lr(const T& v, tree_node<T>* l, tree_node<T>* r)
{
	return new tree_node<T>(v, l, r);
}

template <class T>
tree_node<T>* l(const T& v, tree_node<T>* n)
{
	return new tree_node<T>(v, n, nullptr);
}

template <class T>
tree_node<T>* r(const T& v, tree_node<T>* n)
{
	return new tree_node<T>(v, nullptr, n);
}

template <class T>
tree_node<T>* v(const T& v)
{
	return new tree_node<T>(v, nullptr, nullptr);
}

// 2.e

template <class T>
std::ostream& operator<<(std::ostream& os, const tree_node<T>& t)
{
	os << t.data;
	if (t.left != nullptr) os << "(" << *t.left << ")";
	if (t.right != nullptr) os << "[" << *t.right << "]";
	return os;
}

using namespace std;

// 2.d

int main()
{
	auto t1 =
		shared_ptr<tree_node<int>>(		// usiamo gli shared_ptr per non doverci ricordare di fare delete
			lr(1,						
				lr(2,					// con le funzioni globali è comodissimo costruire un albero
					v(3),
					v(4)),
				r(5,
					lr(6,
						v(7),
						v(8)))));

	auto t2 =
		shared_ptr<tree_node<int>>(
			lr(1,
				r(5,
					lr(6,				// il sottoalbero destro è uguale al sinistro
						v(7),
						v(8))),
				lr(2,
					v(3),
					v(4))));

	// test dell'operatore di stream (<<)
	cout << "pretty printer: " << endl
		<< "t1: " << *t1 << endl	// dereferenziamo per stampare perché il nostro operator<< non vuole un pointer ma un reference
		<< "t2: " << *t2 << endl;

	// test dell'operatore di uguaglianza (==)
	cout << "equality: " << (*t1 == *t2) << ", " << (*t1->left == *t2->right) << endl;	// dereferenziamo gli operandi sinistro e destro del nostro operator== perchè non accetta pointer ma reference

	// test dell'iteratore non-const
	cout << "iterator: ";
	for (tree_node<int>::iterator it = t1->begin(); it != t1->end(); ++it)
	{
		int& n = *it;		// dereferenziando l'iteratore abbiamo accesso non-const al dato dentro il nodo
		n *= 2;				// il campo data in ogni nodo può quindi essere modificato
		cout << n << " ";
	}
	cout << endl << "t1 modificato: " << *t1 << endl;	// ristampiamo t1 dopo le modifiche

	// test dell'iteratore const
	cout << "const iterator: ";
	for (tree_node<int>::const_iterator it = t1->begin(); it != t1->end(); ++it)	// t1->begin() ritorna un iterator, che viene convertito in un const_iterator dal costruttore alla linea 142
	{
		const int& n = *it;	// dereferenziando l'iteratore abbiamo accesso const al dato dentro il nodo, quindi non possiamo modificarlo ma solo leggerlo
		cout << n << " ";
	}
	cout << endl;



}

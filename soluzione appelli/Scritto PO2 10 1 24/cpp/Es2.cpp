// Scritto PO2 10 9 24.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <functional>
#include <vector>
#include <type_traits>

using namespace std;

// 2.a
template <class InputIterator, class OutputIterator>
void map(InputIterator from, InputIterator to, OutputIterator out, function<typename OutputIterator::value_type(typename InputIterator::value_type)> f)
{
    while (from != to)
        *out++ = f(*from++);
}

// 2.b
template <class A, class B>
vector<B> map(const vector<A>& v, function<B(A)> f)
{
    vector<B> r(v.size());
    map(v.begin(), v.end(), r.begin(), f);
    return r;
}

// 2.c
namespace cpp03 {

    // 2.c.i
    template <class A, class B, class F>
    vector<B> map(const vector<A>& v, F f) 
    {
        vector<B> r(v.size());
        for (int i = 0; i < v.size(); ++i)
            r[i] = f(v[i]);
        return r;
    }

    // 2.c.ii
    // no, non possono coesistere, perché sarebbero overload ambigui. Per questo motivo ho dovuto metterli in un namespace a parte.

    // 2.c.iii
    // bisogna usare i function object, cioè oggetti per cui è definito l'operatore di applicazione operator()
    // esempio:
    class myfunction {
    public: 
        bool operator()(int n) { return n > 2; }
    };

    void test()
    {
        vector<int> v1{ 1, 2, 3, 4, 5 };
        vector<bool> v2 = map<int, bool>(v1, myfunction()); // senza le annotazioni esplicite dei template argument non compila (questo non è richiesto nell'esame perché è una cosa molto sottile)
    }
}

// main for testing
//

template <class T>
ostream& operator<<(ostream& os, const vector<T>& v)
{
    os << "[ ";
    for (auto it = v.begin(); it != v.end(); ++it)
        os << *it << ", ";
    os << "\b\b ]";
    return os;
}

int main()
{
    vector<int> v1{ 1, 2, 3, 4, 5 };
    vector<bool> v2(v1.size());
    map(v1.begin(), v1.end(), v2.begin(), [](int n) { return n > 2; });

    v2 = map<int, bool>(v1, [](int n) { return n > 2; }); // anche qui bisogna mettere i template argument espliciti

    cout << v1 << endl << v2 << endl;

    return 0;
}
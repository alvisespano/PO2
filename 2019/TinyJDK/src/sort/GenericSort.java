package sort;

import java.util.*;
import java.util.function.BiFunction;

public class GenericSort {


    static <T> void quicksort8(List<? extends Comparable<T>> l) {

    }

    static <T extends Comparable<T>> void quicksort8(List<T> l) {
        T a = l.get(0), b = l.get(1);
//        if (a.compareTo(b) < 0) ...

    }

    static void quicksort3(List<? extends Comparable<?>> l) {
        Object a = l.get(0), b = l.get(1);
        if (a.< 0)

    }

    static <T> void quicksort(List<T> l, Comparator<T> f) {
        T a = l.get(0), b = l.get(1);
        if (f.compare(a, b) < 0)
        // algo
    }

    static <T> void quicksort__compare(List<T> l, BiFunction<T, T, Integer> f) { // TODO: stessa erasure di BiFunction<T, T, Boolean>
        quicksort(l, new Comparator<T>() {
            @Override
            public int compare(T a, T b) {
                return f.apply(a, b);
            }
        });
    }

    static <T> void quicksort__predicate(List<T> l, BiFunction<T, T, Boolean> isLessThan) {
        quicksort(l, new Comparator<>() {
            @Override
            public int compare(T a, T b) {
                return a.equals(b) ? 0 : isLessThan.apply(a, b) ? -1 : 1;
            }
        });
    }

    // TODO LEZIONE: migliorare e generalizzare sto algoritmo
    int partition(int arr[], int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;

            while (arr[j] > pivot)
                j--;

            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
}

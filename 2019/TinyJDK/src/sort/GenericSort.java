package sort;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GenericSort {


    static <T> void sort(List<T> l, BiFunction<T, T, Integer> f) {
        sort(l, new Comparator<T>() {
            @Override
            public int compare(T a, T b) {
                return f.apply(a, b);
            }
        });
    }

    static <T> void sort__predicate(List<T> l, BiFunction<T, T, Boolean> isLessThan) {
        sort(l, new Comparator<>() {
            @Override
            public int compare(T a, T b) {
                return a.equals(b) ? 0 : isLessThan.apply(a, b) ? -1 : 1;
            }
        });
    }

    public static <T> void sort(List<T> l, Comparator<T> cmp) {
        Object[] a = new Object[l.size()];
        //noinspection unchecked
        sort((T[]) l.toArray(a), cmp);
        for (int i = 0; i < a.length; ++i) {    // TODO: migliorare performance
            l.set(i, (T) a[i]);
        }
    }

    public static <T extends Comparable<T>> void sort(List<T> l) {
        sort(l, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public static <T> void sort(T[] arr, Comparator<T> cmp) {
        quickSort(arr, 0, arr.length - 1, cmp);
    }

    ///////////////////////

    private static <T> int partition(T[] arr, int left, int right, Comparator<T> cmp) {
        int i = left, j = right;
        T pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (cmp.compare(arr[i], pivot) < 0)    // while (arr[i] < pivot)
                i++;

            while (cmp.compare(arr[j], pivot) > 0)
                j--;

            if (i <= j) {
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    private static <T> void quickSort(T[] arr, int left, int right, Comparator<T> cmp) {
        int index = partition(arr, left, right, cmp);
        if (left < index - 1)
            quickSort(arr, left, index - 1, cmp);
        if (index < right)
            quickSort(arr, index, right, cmp);
    }
}

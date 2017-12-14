package ru.mail.polis.sort;


import java.util.Random;

public class QuickSort2<T> extends AbstractSortOnComparisons<T> implements Sort<T> {

    public void sort (T[] array)
    {
        quickSort (array, 0, array.length - 1);
    }

    private void quickSort(T[] a, int left, int right)
    {
        if (left >= right ) return;
        int k;
        T v = a[right];
        int i = left - 1, j = right, p = left - 1, q = right;
        for(;;)
        {
            while (lesser(a[++i], v)) ;
            while (lesser(v,a[--j])) if (j == left) break;
            if (i >= j) break;
            swap(a, i, j);
            if (a[i] == v) { p++; swap(a,p,i); }
            if (v == a[j]) { q--; swap(a,q,j); }
        }
        swap(a, i, right);
        j = i-1;
        i = i+1;
        for (k = left ; k <= p; k++, j --) swap(a,k,j);
        for (k = right-1; k >= q; k--, i++) swap(a, k,i);
        quickSort(a, left, j);
        quickSort(a, i, right);
    }

}

package ru.mail.polis.sort;

import java.util.Random;

public class QuickSort1<T> extends AbstractSortOnComparisons<T> {
    Random r = new Random();
    private void quickSort(T[] a, int left, int right)
    {
        if (left >= right ) return;
        if (right - left <=15)
        {
            insertionSort(a, left,right);
        } else {
            int idx = partition(a, left, right);
            quickSort(a, left, idx - 1);
            quickSort(a, idx, right);
        }
    }

    private void insertionSort(T[] a, int left,int right){
        for (int i = left; i <= right; i++){
            for (int j = i; j > 0 && lesser(a[j],a[j-1]); j--) {
                T x = a[j];
                a[j] = a[j - 1];
                a[j - 1] = x;
            }
        }
    }

    private int partition(T[] a, int left, int right)
    {

        T p = a[r.nextInt(right - left) + left];
        int i = left, j = right;
        while (i <= j)
        {
            while (lesser(a[i],p)) i++;
            while (greater(a[j], p)) j--;
            if (i <= j)
            {
                swap(a,i,j);
                i++;
                j--;
            }
        }
        return i;
    }

    @Override
    public void sort(T[] array) {
        quickSort(array, 0 , array.length - 1);
    }
}

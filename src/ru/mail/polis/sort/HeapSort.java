package ru.mail.polis.sort;

public class HeapSort<T> extends AbstractSortOnComparisons<T>{
    int size;
    T []a;
    public HeapSort(T[] arr){
        a = arr;
        for (int i = a.length / 2; i >=0; i--)
        {
            buildDown(i);
        }
        size = a.length;
    }

    public  void sort()
    {
        T[] res =(T[]) new Object[a.length];
        int i = 0;
        while(!isEmpty())
        {
            res[i++] = getMin();
        }
        a = res;
    }


    void buildUp(int i)
    {
        int parent = (i - 1) / 2;
        while(i > 0 && lesser(a[i],a[parent]))
        {
            SortUtils.swap(a, i, parent);
            i = (parent - 1) / 2;
        }
    }

    void insert(T x)
    {
        size++;
        a[size] = x;
        buildUp(size);
    }

    void buildDown(int i)
    {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int min = i;
        if (left < size && greater(a[min],a[left]))
            min = left;
        if (right < size && greater(a[min],a[right]))
            min = right;
        if (min != i)
        {
            SortUtils.swap(a, min, i);
            buildDown(min);
        }
    }

    T getMin()
    {
        T res = a[0];
        SortUtils.swap(a,0, size - 1);
        size--;
        buildDown(0);
        return res;
    }

    boolean isEmpty()
    {
        return size == 0;
    }


}
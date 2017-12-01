package ru.mail.polis.sort;

public class MergeSort<T> extends AbstractSortOnComparisons<T>{
    private T[] numbers;
    private T[] helper;

    private int number;
    public MergeSort(){
        super();
    }

    private void mergesort(int low, int high) {
        if (low < high) {
            int middle = low + ((high - low) >> 1);
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (compare(helper[i],helper[j]) <= 0) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }

    @Override
    public void sort(T[] array) {
        this.numbers = array;
        number = array.length;
        this.helper = (T[]) new Object[number];
        mergesort(0, number - 1);
    }
}

package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class CountingSort<T extends IntKeyObject> implements Sort<T> {

    private int findMin(T[] a) throws ArrayIndexOutOfBoundsException{
        int min = a[0].getKey();
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i].getKey());
        }
        return min;
    }

    private int findMax(T[] a) throws ArrayIndexOutOfBoundsException{
        int max = a[0].getKey();
        for (int i = 1; i < a.length; i++) {
            max = Math.max(max, a[i].getKey());
        }
        return max;
    }

    public CountingSort() {
        /* empty */
    }

    @Override
    public void sort(T[] array) {

        int max;
        int min;
        try {
            min = findMin(array);
            max = findMax(array);
        } catch (ArrayIndexOutOfBoundsException e){
            return;
        }
        int[] count = new int[(max - min) + 1];
        for (T x : array) {
            count[x.getKey() - min]++;
        }
        for (int i = 1; i <= max - min; i++) {
            count[i] += count[i - 1];
        }
        T[] res = (T[])new IntKeyObject[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            res[--count[array[i].getKey() - min]] = array[i];
        }
        System.arraycopy(res, 0, array, 0, array.length);
    }
}

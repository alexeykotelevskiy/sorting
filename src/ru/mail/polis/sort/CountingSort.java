package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class CountingSort<T extends IntKeyObject> implements Sort<T> {

    int findMax(T[] a) {
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

        int max = findMax(array);
        int[] count = new int[max + 1];
        for (T x : array) {
            count[x.getKey()]++;
        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        T[] res = (T[])new IntKeyObject[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            res[--count[array[i].getKey()]] = array[i];
        }
        System.arraycopy(res, 0, array, 0, array.length);
    }
}

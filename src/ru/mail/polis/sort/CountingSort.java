package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;



public class CountingSort {

    static <V extends IntKeyObject> int findMax(V[] a) {
        int max = a[0].getKey();
        for (int i = 1; i < a.length; i++) {
            max = Math.max(max, a[i].getKey());
        }
        return max;
    }

    public static <V extends IntKeyObject> void sort(V[] a) {
        int max = findMax(a);
        int[] count = new int[max + 1];
        for (V x : a) {
            count[x.getKey()]++;
        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        V[] res = (V[])new IntKeyObject[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            res[--count[a[i].getKey()]] = a[i];
        }
        System.arraycopy(res, 0, a, 0, a.length);
    }



}

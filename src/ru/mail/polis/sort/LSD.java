package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;


public class LSD {
    public static <V extends Numerical>void sort(V[] a) {
        final int r = a[0].getDigitMaxValue();
        int d = a[0].getDigitCount();
        for(int i = 1; i < a.length; i++)
            if(a[i].getDigitCount() > d)
                d = a[i].getDigitCount();

        for (int k = 0; k < d; k++) {
            int[] count = new int[r];
            for (V x : a) {
                count[x.getDigit(k)]++;
            }
            for (int i = 1; i < r; i++) {
                count[i] += count[i - 1];
            }
            V[] res = (V[]) new Numerical[a.length];
            for (int i = a.length - 1; i >= 0; i--) {
                res[--count[a[i].getDigit(k)]] = a[i];
            }
            System.arraycopy(res, 0, a, 0, a.length);
        }
    }


}


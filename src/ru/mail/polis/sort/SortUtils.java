package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ru.mail.polis.structures.IntKeyIntegerValueObject;
import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

public class SortUtils {

    private static final Random r = ThreadLocalRandom.current();

    public static void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static <T>void swap(T[] a, int i,int j)
    {
        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static int[] generateRandomArray(int n)
    {
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++)
            a[i] = r.nextInt(Integer.MAX_VALUE);
        return a;
    }

    public static int[] generateArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(a, i, j);
        }
        return a;
    }

    public static String generateRandomString(int n)
    {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < n; i++){
            int ch = r.nextInt(26);
            a.append((char)(ch + 'a'));
        }
        return a.toString();
    }

    public static String[] generateRandomStringOneSizeArray(int n)
    {
        String[] a = new String[n];
        for (int i=0; i<a.length; i++)
        {
            a[i] = generateRandomString(100);
        }
        return a;
    }


    public static String[] generateRandomStringArray(int n)
    {
        String[] a = new String[n];
        for (int i=0; i<a.length; i++)
        {
            a[i] = generateRandomString(r.nextInt(100) + 1);
        }
        return a;
    }


    public static SimpleString[] generateSimpleString(String[] input)
    {
        SimpleString[] a = new SimpleString[input.length];
        for (int i = 0; i < a.length; i++)
        {
            a[i] = new SimpleString(input[i]);
        }
        return a;
    }


    public static int[] generateManyDouble(int n)
    {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = r.nextInt(n/(n/100));
        return a;
    }

    public static Integer[] generateObjArray(int[] a)
    {
        return Arrays.stream( a ).boxed().toArray(Integer[]::new);
    }


    public static IntKeyStringValueObject[] generateIntKeyStrArray(String[] input, int keyRange)
    {
        IntKeyStringValueObject[] res = new IntKeyStringValueObject[input.length];
        for (int i=0;i<input.length;i++)
        {
            res[i] = new IntKeyStringValueObject(r.nextInt(keyRange), input[i]);
        }
        return res;
    }


    public static IntKeyIntegerValueObject[] generateIntKeyArray(int[] input)
    {
        IntKeyIntegerValueObject[] res = new IntKeyIntegerValueObject[input.length];
        for (int i = 0; i < input.length; i++)
        {
            res[i] = new IntKeyIntegerValueObject(input[i], r.nextInt());
        }
        return res;
    }

    public static SimpleInteger[] SimpleIntegerGenerate(int[] input)
    {
        SimpleInteger[] res = new SimpleInteger[input.length];
        for (int i = 0; i< input.length; i++)
            res[i] = new SimpleInteger(input[i]);
        return res;
    }

    public static boolean isArraySorted(int[] a) {
        boolean isSorted = true;
        for (int i = 0; i < a.length - 1 && isSorted; i++) {
            isSorted = a[i] <= a[i + 1];
        }
        return isSorted;
    }

    public static <T extends Comparable<? super T>> boolean isArraySorted(T[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = array[i].compareTo(array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static <T> boolean isArraySorted(T[] array, Comparator<T> comparator) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = comparator.compare(array[i], array[i + 1]) <= 0;
        }
        return isSorted;
    }
}

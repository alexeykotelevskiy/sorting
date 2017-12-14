package ru.mail.polis.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import ru.mail.polis.structures.IntKeyIntegerValueObject;

import static org.junit.Assert.*;

public class CountingSortTest {
    private static CountingSort<IntKeyIntegerValueObject> countingSort;

    boolean compareArrays(IntKeyIntegerValueObject[] a, IntKeyIntegerValueObject[] b)
    {
        if (a.length != b.length)
            return false;
        for(int i = 0; i<a.length;i++)
        {
            System.out.println(a[i].getKey() + " " + b[i].getKey());
            if (a[i].getKey() != b[i].getKey()) return false;
        }
        return true;
    }

    @BeforeClass
    public static void create()
    {
        countingSort = new CountingSort<>();
    }
    @Test
    public void sort() throws Exception {
        IntKeyIntegerValueObject[] a,b;
        a = SortUtils.generateIntKeyArray(new int[]{10, 10, -9, 8,-8,-8,-8, 7, 6, 5, 4, 3, 2, 1, 0});
        b = SortUtils.generateIntKeyArray(new int[]{-9,-8,-8,-8, 0 , 1, 2, 3, 4, 5, 6, 7, 8, 10,10});
        countingSort.sort(a);
        assertTrue(compareArrays(a,b));
    }

}
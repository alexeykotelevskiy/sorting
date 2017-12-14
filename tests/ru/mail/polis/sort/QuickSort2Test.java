package ru.mail.polis.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSort2Test {
    private static QuickSort2<Integer> quickSort2;

    @BeforeClass
    public static void create()
    {
        quickSort2 = new QuickSort2<>();
    }


    @Test
    public void sort() throws Exception {
        Integer[] a = {10, 9, 8, 7, 6,6,6,6, 5, 4, 3, 2, 1, 0};
        quickSort2.sort(a);
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6,6,6,6, 7, 8, 9, 10}, a);
    }

}
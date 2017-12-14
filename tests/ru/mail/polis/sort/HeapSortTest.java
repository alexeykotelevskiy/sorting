package ru.mail.polis.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSortTest {
    private static HeapSort<Integer> heapSort;
    @BeforeClass
    public static void create()
    {
        heapSort = new HeapSort<>();
    }

    @Test
    public void sort() throws Exception {
    Integer[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    heapSort.sort(a);
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, a);
    }

    @Test
    public void sort2() throws Exception {
        Integer[] a = {9, 10, 0, 5, 6,  7, 4, 3, 2, 1, 8};
        heapSort.sort(a);
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, a);
    }

}
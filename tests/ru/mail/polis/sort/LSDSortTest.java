package ru.mail.polis.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import static org.junit.Assert.*;

public class LSDSortTest {
    private static LSDSort<Numerical> lsdSort;

    boolean isEq(Numerical[] a, Numerical[] b)
    {
        if (a.length != b.length)
            return false;

        for (int i=0;i<a.length; i++)
        {
            if (a[i].compareTo(b[i])!= 0)
                return false;
        }
        return true;
    }

    @BeforeClass
    public static void create(){
        lsdSort = new LSDSort<>();

    }

    @Test
    public void sort() throws Exception {
        SimpleInteger[] a = SortUtils.SimpleIntegerGenerate(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
        SimpleInteger[] b = SortUtils.SimpleIntegerGenerate(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        lsdSort.sort(a);
        assertTrue(isEq(a,b));
    }

    @Test
    public void sortString(){
        SimpleString[] a = SortUtils.generateSimpleString(new String[]{"abc", "bcd", "cde", "acd", "zxy", "bba"});
        SimpleString[] b = SortUtils.generateSimpleString(new String[]{"abc", "acd", "bba", "bcd", "cde", "zxy"});
        lsdSort.sort(a);
        assertTrue(isEq(a,b));
    }

}
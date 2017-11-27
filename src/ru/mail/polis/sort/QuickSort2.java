package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort2<T> extends AbstractSortOnComparisons<T> {

    public void sort (T[] input){
        sort (input, 0, input.length - 1);
    }

    private void sort(T[] input, int lowIndex, int highIndex) {

        if (highIndex<=lowIndex) return;

        T pivot1=input[lowIndex];
        T pivot2=input[highIndex];


        if (greater(pivot1, pivot2)){
            SortUtils.swap(input, lowIndex, highIndex);
            pivot1=input[lowIndex];
            pivot2=input[highIndex];
        }
        else if (pivot1==pivot2){
            while (pivot1.equals(pivot2) && lowIndex<highIndex){
                lowIndex++;
                pivot1=input[lowIndex];
            }
        }


        int i=lowIndex+1;
        int lt=lowIndex+1;
        int gt=highIndex-1;

        while (i<=gt){

            if (lesser(input[i], pivot1)){
                SortUtils.swap(input, i++, lt++);
            }
            else if (lesser(pivot2, input[i])){
                SortUtils.swap(input, i, gt--);
            }
            else{
                i++;
            }

        }
        SortUtils.swap(input, lowIndex, --lt);
        SortUtils.swap(input, highIndex, ++gt);

        sort(input, lowIndex, lt-1);
        sort (input, lt+1, gt-1);
        sort(input, gt+1, highIndex);

    }
    public static void main(String ...args)
    {
        Integer[] a = Arrays.stream( SortUtils.generateArray(10) ).boxed().toArray( Integer[]::new );
        for (int i=0;i<a.length;i++)
         System.out.print(a[i] + " ");

        QuickSort2<Integer> s= new QuickSort2<>();
        s.sort(a);
        System.out.println();
        for (int i=0;i<a.length;i++)
            System.out.print(a[i] + " ");

    }
}
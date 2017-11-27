package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
//В бенчмарках проверять нужно на объектах с одинаковым количеством разрядов
// (числа одной длины / строки с одинаковым количеством символов)
//Для этих объектов сравнивается время между сортировками extends AbstractSortOnComparisons и LSDSort
public interface Numerical<T> extends Comparable<T> {

    /**
     * @param index - порядковый номер разряда
     * @return Значение index разряда
     * @throws IndexOutOfBoundsException если такого разряда нет
     */
    int getDigit(int index) throws IndexOutOfBoundsException;

    /**
     * @return Возращается максимальное значение разряда (минимальное значение == 0)
     */
    int getDigitMaxValue();

    /**
     * @return Количество разрядов
     */
    int getDigitCount();
}

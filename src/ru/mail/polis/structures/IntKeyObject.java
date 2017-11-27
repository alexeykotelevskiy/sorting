package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 13/11/2017.
 */
//Объекты со значениями ключей в узком диапазоне
//Для этих объектов сравнивается время между сортировками extends AbstractSortOnComparisons и CountingSort
public interface IntKeyObject<V> extends Comparable<IntKeyObject> {

    int getKey();

    V getValue();

    @Override
    default int compareTo(IntKeyObject anotherIntKeyObject) {
        return Integer.compare(getKey(), anotherIntKeyObject.getKey());
    }
}

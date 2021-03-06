package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical<SimpleInteger> {

    private static final int DIGIT_COUNT = 2;

    private final int data;

    public SimpleInteger(Integer data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Source must be not null");
        }
       this.data = data;

    }

    @Override
    public int getDigit(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index " + index);
        } else if (index >= getDigitCount()) {
            return 0;
        } else {
            return (data >> index) & 1;
        }
    }

    @Override
    public int getDigitMaxValue() {
        return DIGIT_COUNT;
    }

    @Override
    public int getDigitCount() {
        //todo
        return Integer.SIZE / 2 - 1;
    }

    @Override
    public int compareTo(SimpleInteger anotherSimpleInteger)
    {
        return Integer.compare(this.data, anotherSimpleInteger.data);
    }
}

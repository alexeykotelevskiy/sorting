package ru.mail.polis.structures;

public class IntKeyIntegerValueObject implements IntKeyObject<Integer>{
    private final int key;
    private final Integer value;


    public IntKeyIntegerValueObject(int key, Integer value) {
        this.key = key;
        this.value = value;
    }

        @Override
        public int getKey() {
            return key;
        }

        @Override
        public Integer getValue() {
            return value;
        }


}

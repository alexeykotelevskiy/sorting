package ru.mail.polis.bench;

public class StateInput {
    enum State{
        RANDOM,
        UNIQUE,
        MANY_DOUBLE,
        STRINGS,
        STRINGS_RANDOM_SIZE
    };
    State currstate;
    int index = 0;
    static final int TEST_COUNT = 10;
    static final int DATA_COUNT = 10000;


}

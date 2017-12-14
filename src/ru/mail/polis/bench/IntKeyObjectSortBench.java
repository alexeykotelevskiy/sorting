package ru.mail.polis.bench;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.Sort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyIntegerValueObject;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.SimpleInteger;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class IntKeyObjectSortBench extends StateInput{

    CountingSort<IntKeyObject> countingSort = new CountingSort<>();
    IntKeyObject[][] data;
    IntKeyObject[] curr;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        currstate = State.STRINGS;


        data = new IntKeyObject[TEST_COUNT][DATA_COUNT];

        if (currstate == State.UNIQUE)
        {
            for (int i = 0; i < TEST_COUNT; i++)
                data[i] = SortUtils.generateIntKeyArray(SortUtils.generateArray(DATA_COUNT));
        }

        if (currstate == State.RANDOM)
        {
            for (int i = 0; i < TEST_COUNT; i++)
                data[i] = SortUtils.generateIntKeyArray(SortUtils.generateRandomArray(DATA_COUNT));
        }

        if (currstate == State.MANY_DOUBLE)
        {
            for (int i = 0; i < TEST_COUNT; i++)
                data[i] = SortUtils.generateIntKeyArray(SortUtils.generateManyDouble(DATA_COUNT));
        }

        if (currstate == State.STRINGS)
        {
            for (int i=0;i<TEST_COUNT;i++)
            {
                data[i] = SortUtils.generateIntKeyStrArray(SortUtils.generateRandomStringOneSizeArray(DATA_COUNT), DATA_COUNT);
            }

        }

        if (currstate == State.STRINGS_RANDOM_SIZE)
        {
            for (int i=0;i<TEST_COUNT;i++)
            {
                data[i] = SortUtils.generateIntKeyStrArray(SortUtils.generateRandomStringArray(DATA_COUNT), DATA_COUNT);
            }
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        index = (index + 1) % TEST_COUNT;
    }


    @Benchmark
    public void measureCountSort()
    {
        countingSort.sort(curr);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IntKeyObjectSortBench.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}

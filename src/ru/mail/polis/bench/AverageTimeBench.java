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

import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;

/**
 * Created by Nechaev Mikhail
 * Since 20/11/16.
 */

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class AverageTimeBench extends StateInput{

    int[][] data;
    int[] curr;
    int index;

    String[][] dataString;
    String[] currString;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        currstate = State.MANY_DOUBLE;
        data = new int[TEST_COUNT][DATA_COUNT];

        if (currstate == State.UNIQUE) {
            for (int i = 0; i < TEST_COUNT; i++) {
                //define arrays here
                data[i] = SortUtils.generateArray(DATA_COUNT);
            }
        }

        if (currstate == State.RANDOM)
        {
            for (int i = 0; i < TEST_COUNT; i++) {
                //define arrays here
                data[i] = SortUtils.generateRandomArray(DATA_COUNT);
            }
        }


        if (currstate == State.MANY_DOUBLE)
        {
            for (int i = 0; i < TEST_COUNT; i++) {
                //define arrays here
                data[i] = SortUtils.generateManyDouble(DATA_COUNT);
            }
        }

    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        index = (index + 1) % 10;
    }

    @Benchmark
    public void measureBubbleSort() {
        BubbleSort.sort(curr);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AverageTimeBench.class.getSimpleName())
                .include(IntKeyObjectSortBench.class.getSimpleName())
                .include(SimpleInteger.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

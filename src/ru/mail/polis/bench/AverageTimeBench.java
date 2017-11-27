package ru.mail.polis.bench;

import java.util.ArrayList;
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
import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.QuickSort2;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyIntegerValueObject;
import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

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
public class AverageTimeBench {
    enum State{
        RANDOM,
        UNIQUE,
        MANY_DOUBLE,
        STRINGS
    };

    State currstate;



    int[][] data;
    int[] curr;
    Integer[] objCurr;
    int index;
    IntKeyIntegerValueObject[][] dataIntKey;
    IntKeyIntegerValueObject[] currIntKey;
    IntKeyStringValueObject[][] dataIntKeyString;
    IntKeyStringValueObject[] currIntKeyString;
    SimpleInteger[][] dataSimpleInteger;
    SimpleInteger[] currSimpleInteger;
    SimpleString[][] dataSimpleString;
    SimpleString[] currSimpleString;



    @Setup(value = Level.Trial)
    public void setUpTrial() {
        currstate=State.RANDOM;

        index = 0;
        data = new int[10][10000];
        dataIntKey = new IntKeyIntegerValueObject[10][10000];
        dataSimpleInteger = new SimpleInteger[10][10000];

        if (currstate == State.RANDOM) {

            for (int i = 0; i < 10; i++) {
                data[i] = SortUtils.generateArray(10000);
                dataIntKey[i] = SortUtils.generateIntKeyArray(data[i]);
                dataSimpleInteger[i] = SortUtils.SimpleIntegerGenerate(data[i]);
            }
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        objCurr = Arrays.stream( curr ).boxed().toArray( Integer[]::new );
        currIntKey = Arrays.copyOf(dataIntKey[index], dataIntKey[index].length);
        currSimpleInteger = Arrays.copyOf(dataSimpleInteger[index],dataSimpleInteger[index].length);
        index%=10;
    }

    @Benchmark
    public void measureBubbleSort() {
        BubbleSort.sort(curr);
    }

    @Benchmark
    public void measureMergeSort(){
        MergeSort<Integer> s = new MergeSort<>();
        s.sort(objCurr);
    }

    @Benchmark
    public void measureHeapSort()
    {
        HeapSort<Integer> s = new HeapSort<>(objCurr);
        s.sort();
    }


    @Benchmark
    public void measureQuickSort1()
    {
        QuickSort1<Integer> s = new QuickSort1<>();
        s.quickSort(objCurr,0,objCurr.length - 1);
    }

    @Benchmark
    public void measureQuickSort2()
    {
        QuickSort2<Integer> s = new QuickSort2<>();
        s.sort(objCurr);
    }

    @Benchmark
    public void measureCountingSort()
    {
        CountingSort.sort(currIntKey);
    }

    @Benchmark
    public void measureLSDSort()
    {
        LSD.sort(currSimpleInteger);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AverageTimeBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

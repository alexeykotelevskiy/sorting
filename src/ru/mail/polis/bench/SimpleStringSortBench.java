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

import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.QuickSort2;
import ru.mail.polis.sort.Sort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

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
public class SimpleStringSortBench extends StateInput {
    //todo: классы extends AbstractSortOnComparisons и LSDSort
    MergeSort<String> mergeSort = new MergeSort<>();
    HeapSort<String> heapSort = new HeapSort<>();
    QuickSort1<String> quickSort1 = new QuickSort1<>();
    QuickSort2<String> quickSort2 = new QuickSort2<>();
    LSDSort<SimpleString> lsd = new LSDSort<>();


    String[][] data;
    String[] curr;
    SimpleString[][] dataSimpleString;
    SimpleString[] currSimpleString;



    @Setup(value = Level.Trial)
    public void setUpTrial() {
        currstate = State.STRINGS_RANDOM_SIZE;

        data = new String[TEST_COUNT][DATA_COUNT];
        dataSimpleString = new SimpleString[TEST_COUNT][DATA_COUNT];

        if (currstate == State.STRINGS)
        {
            for (int i=0; i < TEST_COUNT; i++)
            {
                data[i] = SortUtils.generateRandomStringOneSizeArray(DATA_COUNT);
                dataSimpleString[i] = SortUtils.generateSimpleString(data[i]);
            }
        }




        if (currstate == State.STRINGS_RANDOM_SIZE)
        {
            for (int i=0; i < TEST_COUNT; i++)
            {
                data[i] = SortUtils.generateRandomStringArray(DATA_COUNT);
                dataSimpleString[i] = SortUtils.generateSimpleString(data[i]);
            }
        }

    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        currSimpleString = Arrays.copyOf(dataSimpleString[index], dataSimpleString[index].length);
        index = (index + 1) % TEST_COUNT;
    }

    @Benchmark
    public void measureMergeSort(){
        mergeSort.sort(curr);
    }

    @Benchmark
    public void measureHeapSort()
    {
        heapSort.sort(curr);
    }


    @Benchmark
    public void measureQuickSort1()
    {
         quickSort1.sort(curr);
    }

    @Benchmark
    public void measureQuickSort2()
    {
        quickSort2.sort(curr);
    }


    @Benchmark
    public void measureLSDSort()
    {
        lsd.sort(currSimpleString);
    }


    public static void main(String[] args) throws RunnerException {
       Options opt = new OptionsBuilder()
                .include(SimpleStringSortBench.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

}

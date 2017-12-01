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

import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.QuickSort2;
import ru.mail.polis.sort.SortUtils;
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
public class SimpleIntegerSortBench extends StateInput {
    //todo: классы extends AbstractSortOnComparisons и LSDSort
    MergeSort<Integer> mergeSort = new MergeSort<>();
    HeapSort<Integer> heapSort = new HeapSort<>();
    QuickSort1<Integer> quickSort1 = new QuickSort1<>();
    QuickSort2<Integer> quickSort2 = new QuickSort2<>();
    LSDSort<SimpleInteger> lsd = new LSDSort<>();


    Integer[][] data;
    Integer[] curr;
    SimpleInteger[][] dataSimpleInteger;
    SimpleInteger[] currSimpleInteger;


    @Setup(value = Level.Trial)
    public void setUpTrial() {
        currstate = State.MANY_DOUBLE;

        data = new Integer[TEST_COUNT][DATA_COUNT];
        dataSimpleInteger = new SimpleInteger[TEST_COUNT][DATA_COUNT];
        if (currstate == State.UNIQUE) {
            for (int i = 0; i < TEST_COUNT; i++) {
                data[i] = SortUtils.generateObjArray(SortUtils.generateArray(DATA_COUNT));
                dataSimpleInteger[i] = SortUtils.SimpleIntegerGenerate(SortUtils.generateArray(DATA_COUNT));
            }
        }


        if (currstate == State.RANDOM) {
            for (int i = 0; i < TEST_COUNT; i++) {
                data[i] = SortUtils.generateObjArray(SortUtils.generateRandomArray(DATA_COUNT));
                dataSimpleInteger[i] = SortUtils.SimpleIntegerGenerate(SortUtils.generateRandomArray(DATA_COUNT));
            }
        }

        if (currstate == State.MANY_DOUBLE) {
            for (int i = 0; i < TEST_COUNT; i++) {
                data[i] = SortUtils.generateObjArray(SortUtils.generateManyDouble(DATA_COUNT));
                dataSimpleInteger[i] = SortUtils.SimpleIntegerGenerate(SortUtils.generateManyDouble(DATA_COUNT));
            }
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        currSimpleInteger = Arrays.copyOf(dataSimpleInteger[index], dataSimpleInteger[index].length);
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
        lsd.sort(currSimpleInteger);
    }
}

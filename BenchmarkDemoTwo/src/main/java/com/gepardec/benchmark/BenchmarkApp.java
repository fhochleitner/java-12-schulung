package com.gepardec.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(3)
@Threads(3)
@Warmup(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 7, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BenchmarkApp {

    private Calculator calculator;
    private int[] numbers;

    @Setup(Level.Trial)
    public void setup() {

        System.out.println("setup() >> pid=" + ProcessHandle.current().pid());
        calculator = new Calculator();
        numbers = ThreadLocalRandom.current().ints(999999999, -99, 100).toArray();
    }

    @Benchmark
    public void benchmarkSumForLoop() {
        calculator.sumViaForLoop(numbers);
    }

    @Benchmark
    public void benchmarkSumStream() {
        calculator.sumViaSequentialStream(numbers);
    }

    @Benchmark
    public void benchmarkSumParallelStream() {
        calculator.sumViaParallelStream(numbers);
    }


}

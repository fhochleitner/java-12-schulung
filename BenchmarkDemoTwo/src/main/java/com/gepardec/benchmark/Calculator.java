package com.gepardec.benchmark;

import java.util.Arrays;

public class Calculator {

    public int sumViaForLoop(int... numbers) {

        if(numbers == null){
            return 0;
        }

        int result = 0;
        for (Integer number : numbers) {
            result += number;
        }
        return result;
    }

    public int sumViaSequentialStream(int... numbers) {
        if(numbers == null){
            return 0;
        }

        return Arrays.stream(numbers).sum();
    }

    public int sumViaParallelStream(int... numbers) {
        if(numbers == null){
            return 0;
        }

        return Arrays.stream(numbers).parallel().sum();
    }

}

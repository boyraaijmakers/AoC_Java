package org.boy.aoc.twentytwentyone.exercises;

import java.util.Arrays;

public class day1 extends SolutionTemplate {
    public String pointOne(String input) {
        int[] inputInts = inputToIntArray(input, "\n");

        int count = 0;

        for (int i = 1; i < inputInts.length; i++)
            if (inputInts[i] > inputInts[i-1])
                count++;

        return String.valueOf(count);
    }

    public String pointTwo(String input) {
        int[] inputInts = inputToIntArray(input, "\n");

        int count = 0;

        for (int i = 3; i < inputInts.length; i++)
            if (inputInts[i] > inputInts[i - 3])
                count++;
        
        return String.valueOf(count);
    }

    public int[] inputToIntArray(String input, String delim) {
        return Arrays.stream(input.split(delim)).mapToInt(Integer::parseInt).toArray();
    }
}

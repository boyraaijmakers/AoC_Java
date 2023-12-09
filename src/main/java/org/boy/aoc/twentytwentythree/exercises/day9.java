package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;

public class day9 extends SolutionTemplate {
    public String pointOne(String input) {
        return solve(input, false);
    }

    public String pointTwo(String input) {
        return solve(input, true);
    }

    private String solve(String input, boolean part2) {
        return Arrays.stream(input.split("\n"))
                .map(line -> Arrays.stream(line.split(" "))
                        .mapToLong(Long::parseLong)
                        .toArray()
                )
                .map(line -> getNext(line, part2))
                .reduce(Long::sum)
                .orElse(-1L)
                .toString();
    }

    private static long getNext(long[] vals, boolean part2) {
        boolean allZero = true;
        for(long val: vals)
            if (val != 0L) {
                allZero = false;
                break;
            }
        if(allZero) return 0;

        long[] nextGen = new long[vals.length - 1];

        for(int i = 0; i < vals.length - 1; i++)
            nextGen[i] = vals[i+1] - vals[i];

        return part2 ?
                vals[0] - getNext(nextGen, true) :
                vals[vals.length - 1] + getNext(nextGen, false);
    }
}

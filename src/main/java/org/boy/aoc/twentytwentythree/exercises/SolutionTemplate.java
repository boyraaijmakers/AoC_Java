package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;

public abstract class SolutionTemplate {
    public String pointOne(String input) {
        return "pointOne not implemented";
    }

    public String pointTwo(String input) {
        return "pointTwo not implemented";
    }

    public int[] inputToIntArray(String input, String delim) {
        return Arrays.stream(input.split(delim)).mapToInt(Integer::parseInt).toArray();
    }

    public static void print(Object object) {
        print(object, true);
    }

    public static void print(Object object, boolean newline) {
        if (newline) System.out.println(object);
        else System.out.print(object);
    }
}

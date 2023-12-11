package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class day11 extends SolutionTemplate {
    public String pointOne(String input) {
        return solve(input, 2);
    }

    public String pointTwo(String input) {
        return solve(input, 1000000);
    }

    private String solve (String input, int gap) {
        String[] lines = input.split("\n");
        char[][] chars = new char[lines.length][lines[0].length()];

        for(int i = 0; i < lines.length; i++)
            for(int j = 0; j < lines[0].length(); j++)
                chars[i][j] = lines[i].charAt(j);

        HashSet<Integer> emptyLines = new HashSet<>();
        HashSet<Integer> emptyColumns = new HashSet<>();

        for(int i = 0; i < chars.length; i++) {
            char[] line = chars[i];
            boolean allEmpty = true;
            for (char c: line)
                if (c == '#') {
                    allEmpty = false;
                    break;
                }
            if(allEmpty) emptyLines.add(i);
        }

        for(int i = 0; i < chars[0].length; i++) {
            boolean allEmpty = true;
            for (char[] aChar : chars)
                if (aChar[i] == '#') {
                    allEmpty = false;
                    break;
                }
            if(allEmpty) emptyColumns.add(i);
        }

        HashSet<Long> coords = new HashSet<>();

        for(int i = 0; i < chars[0].length; i++)
            for (int j = 0; j < chars.length; j++)
                if(chars[i][j] == '#') {
                    int checkI = i;
                    int checkJ = j;
                    long eL = emptyLines.stream().filter(e -> e < checkI).count();
                    long eC = emptyColumns.stream().filter(e -> e < checkJ).count();

                    coords.add((i + eL * (gap - 1)) * 1000000000 + j + eC * (gap - 1));
                }

        long sum = 0;

        for(Long coord1: coords)
            for(Long coord2: coords)
                sum += Math.abs(coord1 / 1000000000 - coord2 / 1000000000) + Math.abs(coord1 % 1000000000 - coord2 % 1000000000);

        return String.valueOf(sum / 2);
    }
}

package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day4 extends SolutionTemplate {
    public String pointOne(String input) {
        return Arrays.stream(input.split("\n"))
                .map(s -> s.split(": ")[1])
                .map(day4::getScore)
                .map(i -> (int) Math.pow(2, i - 1))
                .reduce(Integer::sum)
                .orElse(-1)
                .toString();
    }

    public String pointTwo(String input) {
        String[] lines = input.split("\n");
        HashMap<Integer, Integer> seen = new HashMap<>();

        for(int val = lines.length - 1; val >= 0; val--) {
            String line = lines[val].split(": ")[1];
            int currScore = 1;

            for(int i = 1; i <= getScore(line); i++) currScore += seen.get(val + i);

            seen.put(val, currScore);
        }

        return seen.values().stream().reduce(Integer::sum).orElse(-1).toString();
    }

    private static int getScore(String line) {
        String[] parts = line.split(" \\| ");
        HashSet<String> winning = new HashSet<>(
                List.of(parts[0].trim().replace("  ", " ").split(" "))
        );
        HashSet<String> yours = new HashSet<>(
                List.of(parts[1].trim().replace("  ", " ").split(" "))
        );

        return (int) yours.stream().filter(winning::contains).count();
    }

}

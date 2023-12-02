package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashMap;

public class day2 extends SolutionTemplate {
    public String pointOne(String input) {
        return Arrays.stream(input.split("\n"))
                .map(day2::parseLine1)
                .reduce(Integer::sum)
                .orElse(-1)
                .toString();
    }

    public String pointTwo(String input) {
        return Arrays.stream(input.split("\n"))
                .map(day2::parseLine2)
                .reduce(Integer::sum)
                .orElse(-1)
                .toString();
    }

    private static int parseLine1 (String line) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("red", 12);
        map.put("green", 13);
        map.put("blue", 14);

        line = line.replace(";", ",");
        String[] parts = line.split(": ");
        boolean validGame = true;

        for(String draw: parts[1].split(", ")) {
            String[] split = draw.split(" ");
            if (map.get(split[1]) < Integer.parseInt(split[0]))
                validGame = false;
        }

        return validGame ? Integer.parseInt(parts[0].split(" ")[1]) : 0;
    }

    private static int parseLine2(String line) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("red", 0);
        map.put("green", 0);
        map.put("blue", 0);

        line = line.replace(";", ",");
        String[] parts = line.split(": ");

        for(String draw: parts[1].split(", ")) {
            String[] split = draw.split(" ");
            map.put(
                    split[1],
                    Math.max(
                            map.get(split[1]),
                            Integer.parseInt(split[0])
                    )
            );
        }

        return map.get("red") * map.get("green") * map.get("blue");
    }

}

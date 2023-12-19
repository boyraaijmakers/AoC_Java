package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day19 extends SolutionTemplate {

    public String pointOne(String input) {
        String[] parts = input.split("\n\n");
        HashMap<String, String> rules = parseRules(input);

        return String.valueOf(Arrays.stream(parts[1].split("\n"))
                .mapToInt(s -> mapXmas(s, rules))
                .reduce(Integer::sum)
                .orElse(-1));
    }

    private HashMap<String, String> parseRules(String input) {
        String[] parts = input.split("\n\n");
        HashMap<String, String> rules = new HashMap<>();

        for(String line: parts[0].split("\n")) {
            String[] lineParts = line.split("[{}]");
            rules.put(lineParts[0], lineParts[1]);
        }
        return rules;
    }

    private int mapXmas(String line, HashMap<String, String> rules) {
        HashMap<Character, Integer> parts = new HashMap<>();

        for (String part: line.split("[{,}]")) {
            if(part.isBlank()) continue;
            parts.put(part.charAt(0), Integer.parseInt(part.substring(2)));
        }

        String currentFlow = "in";

        while (!currentFlow.equals("A") && !currentFlow.equals("R")) {
            String rule = rules.get(currentFlow);
            String[] checks = rule.split(",");

            for (String check : checks) {
                String[] checkParts = check.split(":");

                if (checkParts.length == 1) {
                    currentFlow = check;
                    break;
                }

                char box = check.charAt(0);
                char type = check.charAt(1);
                int val = Integer.parseInt(checkParts[0].substring(2));

                if ((type == '>' && parts.get(box) > val) || (type == '<' && parts.get(box) < val)) {
                    currentFlow = checkParts[1];
                    break;
                }
            }
        }

        return currentFlow.equals("A") ? parts.values().stream().reduce(Integer::sum).orElse(-1) : 0;
    }

    public String pointTwo(String input) {
        HashMap<String, String> rules = parseRules(input);
        HashMap<Character, Integer> minValues = new HashMap<>();
        HashMap<Character, Integer> maxValues = new HashMap<>();

        for(char c: "xmas".toCharArray()) {
            minValues.put(c, 1);
            maxValues.put(c, 4000);
        }

        return String.valueOf(numberOfAccepts("in", rules, minValues, maxValues));
    }

    private long numberOfAccepts(String currentFlow, HashMap<String, String> rules,
                                 HashMap<Character, Integer> minValues,
                                 HashMap<Character, Integer> maxValues) {

        if(currentFlow.equals("A")) {
            long amount = 1L;
            for(char c: "xmas".toCharArray()) amount *= maxValues.get(c) - minValues.get(c) + 1;
            return amount;
        }
        if (currentFlow.equals("R")) return 0L;

        long totalSplit = 0L;

        for(String split: rules.get(currentFlow).split(",")) {
            HashMap<Character, Integer> minValuesSplit = new HashMap<>(minValues);
            HashMap<Character, Integer> maxValuesSplit = new HashMap<>(maxValues);

            String[] splitSplit = split.split(":");

            if(splitSplit.length > 1) {
                char splitChar = split.charAt(0);
                char type = split.charAt(1);
                int val = Integer.parseInt(splitSplit[0].substring(2));

                if(type == '>' && val > minValues.get(splitChar)) {
                    minValuesSplit.put(splitChar, val + 1);
                    maxValues.put(splitChar, val);
                }
                else if (type == '<' && val < maxValues.get(splitChar)) {
                    maxValuesSplit.put(splitChar, val - 1);
                    minValues.put(splitChar, val);
                }
            }

            totalSplit += numberOfAccepts(splitSplit[splitSplit.length - 1], rules, minValuesSplit, maxValuesSplit);
        }

        return totalSplit;
    }


}
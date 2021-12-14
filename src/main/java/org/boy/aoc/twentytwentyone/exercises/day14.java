package org.boy.aoc.twentytwentyone.exercises;


import java.util.HashMap;

public class day14 extends SolutionTemplate {

    public String pointOne(String input) {
        return String.valueOf(getScore(input, 10));
    }

    public String pointTwo(String input) {
        return String.valueOf(getScore(input, 40));
    }

    private long getScore(String input, int generation) {
        String[] inputParts = input.split("\n\n");

        HashMap<String, Long> counts = initInput(inputParts[0]);
        HashMap<String, String> mapping = initMapping(inputParts[1]);
        HashMap<String, Long> letterCounts = new HashMap<>();

        for (int i = 0; i < inputParts[0].length(); i++) {
            String el = inputParts[0].substring(i, i+1);
            letterCounts.put(el, letterCounts.containsKey(el) ?
                    letterCounts.get(el) + 1 : 1);
        }

        HashMap<String, Long> newCounts;

        for (int i = 0; i < generation; i++) {
            newCounts = new HashMap<>();
            for (String pair: counts.keySet()) {
                if (mapping.containsKey(pair)) {
                    String newChar = mapping.get(pair);
                    letterCounts.put(newChar, letterCounts.containsKey(newChar) ?
                            letterCounts.get(newChar) + counts.get(pair) : counts.get(pair));

                    String left = pair.charAt(0) + newChar;
                    String right = newChar + pair.charAt(1);

                    newCounts.put(left, newCounts.containsKey(left) ?
                            newCounts.get(left) + counts.get(pair) : counts.get(pair) );
                    newCounts.put(right, newCounts.containsKey(right) ?
                            newCounts.get(right) + counts.get(pair) : counts.get(pair) );
                } else {
                    newCounts.put(pair, counts.get(pair));
                }
            }
            counts = newCounts;
        }

        long minCount = Long.MAX_VALUE;
        long maxCount = Long.MIN_VALUE;

        for (String letter: letterCounts.keySet()) {
            minCount = Long.min(minCount, letterCounts.get(letter));
            maxCount = Long.max(maxCount, letterCounts.get(letter));
        }

        return maxCount - minCount;
    }

    private HashMap<String, Long> initInput(String inputString) {
        HashMap<String, Long> counts = new HashMap<>();

        for (int i = 0; i < inputString.length() - 1; i++) {
            String pair = inputString.substring(i, i + 2);
            if (counts.containsKey(pair)) counts.put(pair, counts.get(pair) + 1);
            else counts.put(pair, 1L);
        }
        return counts;
    }

    private HashMap<String, String> initMapping(String inputMapping) {
        HashMap<String, String> mapping = new HashMap<>();

        for (String line: inputMapping.split("\n")) {
            String[] parts = line.split(" -> ");
            mapping.put(parts[0], parts[1]);
        }
        return mapping;
    }


}



package org.boy.aoc.twentytwentyone.exercises;


import java.util.HashMap;

public class day6 extends SolutionTemplate {
    public String pointOne(String input) {
        return String.valueOf(getCount(input, 80));
    }

    public String pointTwo(String input) {
        return String.valueOf(getCount(input, 256));
    }

    private long getCount(String input, int depth) {
        HashMap<String, Long> gens = new HashMap<>();
        long finalCount = 0;

        for (String el: input.split(",")) {
            if (!gens.containsKey(el))
                gens.put(el, getGeneration(Integer.parseInt(el), depth));

            finalCount += gens.get(el);
        }
        return finalCount;
    }

    private long getGeneration(int start, int level) {
        HashMap<Integer, Long> counts = new HashMap<>();
        counts.put(start, 1L);

        for (int i = 0; i < level; i++) {
            HashMap<Integer, Long> nextCounts = new HashMap<>();
            for (int j = 0; j < 8; j++) nextCounts.put(j, 0L);

            for (int key: counts.keySet()) {
                if (key == 0) {
                    nextCounts.put(6, nextCounts.get(6) + counts.get(key));
                    nextCounts.put(8, counts.get(key));
                } else {
                    nextCounts.put(key - 1, nextCounts.get(key - 1) + counts.get(key));
                }
            }
            counts = nextCounts;
        }

        long sum = 0;
        for (long val: counts.values())
            sum += val;

        return sum;
    }

}



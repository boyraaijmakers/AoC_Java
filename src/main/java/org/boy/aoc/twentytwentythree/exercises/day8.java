package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day8 extends SolutionTemplate {
    public String pointOne(String input) {
        return solve(input, false);

    }

    public String pointTwo(String input) {
        return solve(input, true);
    }

    private String solve(String input, boolean part2) {
        String[] lines = input.split("\n\n");
        List<String> starts = new ArrayList<>();

        String seq = lines[0];

        HashMap<String, String[]> mapping = new HashMap<>();
        for(String line: lines[1].split("\n")) {
            String[] split = line.split(" = \\(|, |\\)");
            mapping.put(split[0], new String[] {split[1], split[2]});

            if(part2 && split[0].charAt(2) == 'A') starts.add(split[0]);
        }

        if(!part2) starts.add("AAA");
        long lcm = 1L;

        for(String start: starts) {
            String curr = start;
            long steps = 0;
            boolean going = true;

            while (going)
                for (char step : seq.toCharArray()) {
                    steps++;
                    curr = mapping.get(curr)[step == 'L' ? 0 : 1];

                    if (curr.charAt(2) == 'Z') {
                        going = false;
                        lcm = lcm(lcm, steps);
                        break;
                    }
                }

        }

        return String.valueOf(lcm);
    }

    private static long lcm(long a, long b) {
        long absHigherNumber = Math.max(a, b);
        long absLowerNumber = Math.min(a, b);
        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0) lcm += absHigherNumber;

        return lcm;
    }

}

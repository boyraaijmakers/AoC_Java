package org.boy.aoc.twentytwentythree.exercises;

public class day6 extends SolutionTemplate {
    public String pointOne(String input) {
        return solve(input, false);
    }

    public String pointTwo(String input) {
        return solve(input, true);
    }

    private String solve(String input, boolean part2) {
        input = input.replaceAll(" +", " ");
        String[] lines = input.split("\n");

        if(part2) {
            lines[0] = lines[0].replace(" ", "");
            lines[1] = lines[1].replace(" ", "");
        }

        String[] times = lines[0].split(":")[1].trim().split(" ");
        String[] dists = lines[1].split(":")[1].trim().split(" ");

        long sum = 1L;

        for(int i = 0; i < times.length; i++) {
            float time = Float.parseFloat(times[i]);
            float dist = Float.parseFloat(dists[i]);

            int x = (int) Math.ceil((time - Math.sqrt(time * time - 4*dist)) / 2);
            sum *= (long) (time - x - (x-1));
        }

        return String.valueOf(sum);
    }
}

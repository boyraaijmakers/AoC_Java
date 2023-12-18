package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day17 extends SolutionTemplate {

    public String pointOne(String input) {
        return solve(input, false);
    }

    public String pointTwo(String input) {
        return solve(input, true);
    }

    private String solve(String input, boolean part2) {
        String[] lines = input.split("\n");
        int[][] grid = new int[lines.length][lines[0].length()];
        for(int i = 0; i < lines.length; i++)
            for (int j = 0; j < lines[0].length(); j++)
                grid[i][j] = lines[i].charAt(j) - '0';

        int start = 0;
        int end = (lines.length - 1) * 1000 + lines[0].length() - 1;
        int minSteps = part2 ? 4 : 1;
        int maxStpes = part2 ? 10 : 3;

        return String.valueOf(getPath(start, end, grid, minSteps, maxStpes));
    }

    private int getPath(int start, int end, int[][] grid, int minSteps, int maxSteps) {
        Queue<String> queue = new PriorityQueue<>(
                Comparator.comparingInt((String o) -> Integer.parseInt(o.split(",")[2]))
        );
        HashSet<String> seen = new HashSet<>();

        queue.add(start + ",1,0");
        queue.add(start + ",1000,0");

        while(!queue.isEmpty()) {
            print(queue.peek());
            String[] el = queue.poll().split(",");

            int position = Integer.parseInt(el[0]);
            int delta = Integer.parseInt(el[1]);
            int currentHeat = Integer.parseInt(el[2]);

            if(position == end) return currentHeat;

            if(seen.contains(position + "," + delta)) continue;
            seen.add(position + "," + delta);

            for(int i = minSteps; i <= maxSteps; i ++) {
                try {
                    currentHeat += grid[position / 1000][position % 1000];
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }

                int newPos = position + (i * delta);

                queue.add(newPos + "," + delta + "," + currentHeat);

                if(Math.abs(delta) == 1000) {
                    queue.add(newPos + "," + -1 + "," + 0 + "," + currentHeat);
                    queue.add(newPos + "," + 1 + "," + 0 + "," + currentHeat);
                } else {
                    queue.add(newPos + "," + -1000 + "," + 0 + "," + currentHeat);
                    queue.add(newPos + "," + 1000 + "," + 0 + "," + currentHeat);
                }
            }
        }

        return -1;
    }

}

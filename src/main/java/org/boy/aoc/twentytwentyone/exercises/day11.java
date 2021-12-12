package org.boy.aoc.twentytwentyone.exercises;


import java.util.*;

public class day11 extends SolutionTemplate {

    public String pointOne(String input) {
        int[][] grid = parseGrid(input);
        int totalFlash = 0;

        for (int i = 0; i < 100; i++)
            totalFlash += makeGeneration(grid);

        return String.valueOf(totalFlash);
    }

    public String pointTwo(String input) {
        int[][] grid = parseGrid(input);
        int generation = 0;

        while (true) {
            generation++;
            if (makeGeneration(grid) == 100)
                return String.valueOf(generation);
        }
    }

    private void printGrid(int[][] grid) {
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) print(grid[i][j], false);
            print("");
        }
    }

    private int[][] parseGrid(String input) {
        String[] lines = input.split("\n");
        int[][] grid = new int[lines.length + 2][lines[0].length() + 2];

        for (int i = 0; i < lines.length; i++)
            for (int j = 0; j < lines[0].length(); j++)
                grid[i+1][j+1] = Integer.parseInt(lines[i].substring(j, j+1));

        return grid;
    }

    private int makeGeneration(int[][] grid) {
        for (int i = 1; i < grid.length - 1; i++)
            for (int j = 1; j < grid[0].length - 1; j++)
                grid[i][j]++;

        HashSet<String> flashes = new HashSet<>();
        boolean stillFlashing = true;

        while (stillFlashing) {
            stillFlashing = false;

            for (int i = 1; i < grid.length - 1; i++) {
                for (int j = 1; j < grid[0].length - 1; j++) {
                    if (grid[i][j] > 9 && !flashes.contains(coord(i, j))) {
                        stillFlashing = true;
                        flashes.add(coord(i, j));
                        grid[i][j] = 0;

                        for(int k = -1; k <= 1; k++)
                            for(int l = -1; l <= 1; l++)
                                if (!flashes.contains(coord(i+k, j+l)))
                                    grid[i+k][j+l]++;
                    }
                }
            }
        }

        return flashes.size();
    }

    private String coord(int x, int y){
        return String.format("%s,%s", x, y);
    }

}



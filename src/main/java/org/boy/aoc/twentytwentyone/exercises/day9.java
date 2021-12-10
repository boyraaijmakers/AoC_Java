package org.boy.aoc.twentytwentyone.exercises;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class day9 extends SolutionTemplate {

    public String pointOne(String input) {
        int[][] grid = initGrid(input, false);

        int riskTotal = 0;
        for (int y = 1; y < grid[0].length - 1; y++)
            for (int x = 1; x < grid.length - 1; x++)
                if (isMin(grid[x][y], new int[]{grid[x+1][y], grid[x-1][y], grid[x][y+1], grid[x][y-1]}))
                    riskTotal += grid[x][y] + 1;

        return String.valueOf(riskTotal);
    }

    public String pointTwo(String input) {
        int[][] grid = initGrid(input, true);

        ArrayList<Integer> lakeSizes = new ArrayList<>();

        int lakeId = 2;
        for (int y = 1; y < grid[0].length - 1; y++)
            for (int x = 1; x < grid.length - 1; x++) {
                if (grid[x][y] == 0) {
                    if (grid[x-1][y] > 1) grid[x][y] = grid[x-1][y];
                    else if (grid[x][y-1] > 1) grid[x][y] = grid[x][y-1];
                    else {
                        grid[x][y] = lakeId;
                        lakeId++;
                    }
                    int[] neighbours = {grid[x-1][y], grid[x+1][y], grid[x][y-1], grid[x][y+1]};
                    for (int neighbour: neighbours)
                        if (neighbour > 1 && neighbour != grid[x][y])
                            mergeLakes(grid, grid[x][y], neighbour);
                }
            }

        for (int i = 2; i <= lakeId; i++) {
            int lakeSize = 0;
            for (int y = 1; y < grid[0].length - 1; y++)
                for (int x = 1; x < grid.length - 1; x++)
                    if (grid[x][y] == i) lakeSize++;
            lakeSizes.add(lakeSize);
        }

        Collections.sort(lakeSizes);
        Collections.reverse(lakeSizes);

        return String.valueOf(lakeSizes.get(0) * lakeSizes.get(1) * lakeSizes.get(2));
    }

    private int[][] initGrid(String input, boolean bw) {
        String[] lines = input.split("\n");
        int length = lines.length;
        int width = lines[0].length();

        int[][] grid = new int[width + 2][length + 2];
        for (int[] gridLine: grid)
            Arrays.fill(gridLine, bw ? 1 : 10);

        for (int y = 0; y < length; y++)
            for (int x = 0; x < width; x++) {
                int val = Integer.parseInt(lines[y].substring(x, x+1));
                grid[x+1][y+1] = bw ? (val == 9 ? 1 : 0) : val;
            }

        return grid;
    }

    private boolean isMin (int target, int[] neighbours) {
        for (int neighbour: neighbours)
            if (Math.min(target, neighbour) == neighbour)
                return false;
        return true;
    }

    private void mergeLakes(int[][] grid, int lakeA, int lakeB) {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == lakeA) grid[i][j] = lakeB;
    }
}



package org.boy.aoc.twentytwentyone.exercises;


public class day13 extends SolutionTemplate {

    public String pointOne(String input) {
        boolean[][] grid = makeFolds(input, 1);

        int totalPoints = 0;
        for (boolean[] booleans : grid)
            for (int j = 0; j < grid[0].length; j++) totalPoints += booleans[j] ? 1 : 0;

        return String.valueOf(totalPoints);
    }

    public String pointTwo(String input) {
        boolean[][] grid = makeFolds(input, -1);
        printGrid(grid);
        return "";
    }

    private void printGrid(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) print(grid[i][j] ? "X" : " ", false);
            print("");
        }
    }

    private boolean[][] makeFolds(String input, int numFolds) {
        String[] inputParts = input.split("\n\n");
        String[] coords = inputParts[0].split("\n");
        String[] folds = inputParts[1].split("\n");

        numFolds = (numFolds == -1) ? folds.length : numFolds;

        boolean[][]grid = new boolean[2000][2000];

        for (String coord: coords) {
            String[] coordParts = coord.split(",");
            grid[Integer.parseInt(coordParts[1].strip())][Integer.parseInt(coordParts[0].strip())] = true;
        }

        for (int i = 0; i < numFolds; i++) {
            String foldParts = folds[i].split(" ")[2];
            String[] foldInfo = foldParts.split("=");

            grid = fold(grid, foldInfo[0], Integer.parseInt(foldInfo[1].strip()));
        }
        return grid;
    }

    private boolean[][] fold(boolean[][]grid, String dir, int index) {
        boolean[][] newGrid;

        if (dir.equals("x")) {
            newGrid = new boolean[grid.length][index];

            for (int j = 0; j < grid.length; j++) {
                System.arraycopy(grid[j], 0, newGrid[j], 0, index);

                for (int i = index + 1; i < grid[0].length; i++)
                    if (grid[j][i]) newGrid[j][2 * index - i] = true;
            }
        } else {
            newGrid = new boolean[index][grid[0].length];

            for (int i = 0; i < grid[0].length; i++) {
                for (int j = 0; j < index; j++)
                    newGrid[j][i] = grid[j][i];

                for (int j = index + 1; j < grid.length; j++)
                    if (grid[j][i]) newGrid[2 * index - j][i] = true;
            }
        }

        return newGrid;
    }
}



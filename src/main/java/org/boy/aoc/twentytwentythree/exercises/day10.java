package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashSet;

public class day10 extends SolutionTemplate {
    public String pointOne(String input) {

        String[] lines = input.split("\n");

        int[] start = new int[2];

        for(int i = 0; i < lines.length; i++)
            for (int j = 0; j < lines[0].length(); j++)
                if(lines[i].charAt(j) == 'S') start = new int[] {i, j};

        int dir = 1;
        int[] current = new int[] {start[0] - 1, start[1]};
        int steps = 0;

        // 1 UP, 2 RIGHT, 3 DOWN, 4 LEFT

        while(true) {
            steps++;
            print(current[0] + " " + current[1] + " " + dir + " " + lines[current[0]].charAt(current[1]));
            switch (lines[current[0]].charAt(current[1])) {
                case 'S': return String.valueOf(steps / 2);
                case '|': {
                    current[0] = current[0] + (dir == 1 ?  -1 : 1);
                    break;
                }
                case '-': {
                    current[1] = current[1] + (dir == 2 ?  1 : -1);
                    break;
                }
                case 'L': {
                    current[0] = current[0] + (dir == 4 ?  -1 : 0);
                    current[1] = current[1] + (dir == 3 ?  1 : 0);
                    dir = dir == 3 ? 2 : 1;
                    break;
                }
                case 'J': {
                    current[0] = current[0] + (dir == 2 ?  -1 : 0);
                    current[1] = current[1] + (dir == 3 ?  -1 : 0);
                    dir = dir == 2 ? 1 : 4;
                    break;
                }
                case '7': {
                    current[0] = current[0] + (dir == 2 ?  1 : 0);
                    current[1] = current[1] + (dir == 1 ?  -1 : 0);
                    dir = dir == 1 ? 4 : 3;
                    break;
                }
                case 'F': {
                    current[0] = current[0] + (dir == 4 ?  1 : 0);
                    current[1] = current[1] + (dir == 1 ?  1 : 0);
                    dir = dir == 1 ? 2 : 3;
                    break;
                }
            }
        }
    }

    public String pointTwo(String input) {
        String[] lines = input.split("\n");

        int[] start = new int[2];

        for(int i = 0; i < lines.length; i++)
            for (int j = 0; j < lines[0].length(); j++)
                if(lines[i].charAt(j) == 'S') start = new int[] {i, j};

        boolean done = false;

        HashSet <Integer> pipe = new HashSet<>();
        pipe.add(start[0] * 2000 + start[1] * 2);

        int dir = 2;
        int[] current = new int[] {start[0], start[1] + 1};
        pipe.add(start[0] * 2000 + start[1] * 2 + 1);

        // 1 UP, 2 RIGHT, 3 DOWN, 4 LEFT

        while(!done) {
            pipe.add(current[0] * 2000 + 2 * current[1]);
            switch (lines[current[0]].charAt(current[1])) {
                case 'S': done = true; break;
                case '|': {
                    pipe.add(current[0] * 2000 + (dir == 1 ? -1000 : 1000) + 2 * current[1]);
                    current[0] = current[0] + (dir == 1 ?  -1 : 1);
                    break;
                }
                case '-': {
                    pipe.add(current[0] * 2000 + (dir == 2 ? 1 : -1) + 2 * current[1]);
                    current[1] = current[1] + (dir == 2 ?  1 : -1);
                    break;
                }
                case 'L': {
                    pipe.add(current[0] * 2000 + (dir == 4 ? -1000 : 0) + 2 * current[1] + (dir == 3 ?  1 : 0));
                    current[0] = current[0] + (dir == 4 ?  -1 : 0);
                    current[1] = current[1] + (dir == 3 ?  1 : 0);
                    dir = dir == 3 ? 2 : 1;
                    break;
                }
                case 'J': {
                    pipe.add(current[0] * 2000 + (dir == 2 ? -1000 : 0) + 2 * current[1] + (dir == 3 ?  -1 : 0));
                    current[0] = current[0] + (dir == 2 ?  -1 : 0);
                    current[1] = current[1] + (dir == 3 ?  -1 : 0);
                    dir = dir == 2 ? 1 : 4;
                    break;
                }
                case '7': {
                    pipe.add(current[0] * 2000 + (dir == 2 ? 1000 : 0) + 2 * current[1] + (dir == 1 ?  -1 : 0));
                    current[0] = current[0] + (dir == 2 ?  1 : 0);
                    current[1] = current[1] + (dir == 1 ?  -1 : 0);
                    dir = dir == 1 ? 4 : 3;
                    break;
                }
                case 'F': {
                    pipe.add(current[0] * 2000 + (dir == 4 ? 1000 : 0) + 2 * current[1] + (dir == 1 ?  1 : 0));
                    current[0] = current[0] + (dir == 4 ?  1 : 0);
                    current[1] = current[1] + (dir == 1 ?  1 : 0);
                    dir = dir == 1 ? 2 : 3;
                    break;
                }
            }
        }

        int gridX = lines[0].length() * 2;
        int gridY = lines.length * 2;

        HashSet<Integer> inside = new HashSet<>();
        HashSet<Integer> outside = new HashSet<>();

        for(int i = 0; i < gridY; i++) {
            for (int j = 0; j < gridX; j++) {
                if(pipe.contains(i * 1000 + j)) continue;
                if(i % 2 == 0 && j % 2 == 0) {
                    if (checkTile(new int[]{i, j}, pipe)) inside.add(i * 1000 + j);
                    else outside.add(i * 1000 + j);
                }
                else inside.add(i * 1000 + j);
            }
        }

        int outsides = gridX * gridY;

        while (outsides != outside.size()) {
            outsides = outside.size();
            HashSet<Integer> newIn = new HashSet<>();
            for(int in: inside) {
                if(
                        outside.contains(in + 1000) || outside.contains(in - 1000) ||
                        outside.contains(in + 1) || outside.contains(in - 1)
                ) outside.add(in);
                else
                    newIn.add(in);
            }

            inside = newIn;
        }

        return String.valueOf(
                inside.stream()
                        .filter(el -> el % 2 == 0 && (el / 1000) % 2 == 0)
                        .count()
        );
    }

    private boolean checkTile(int[] point, HashSet<Integer> pipe) {
        return checkDir(point.clone(), pipe, -1, 0) &&
                checkDir(point.clone(), pipe, 1, 0) &&
                checkDir(point.clone(), pipe, 0, -1) &&
                checkDir(point.clone(), pipe, 0, 1);
    }

    private boolean checkDir(int[] point, HashSet<Integer> pipe, int deltaX, int deltaY) {

        while (point[0] >= 0 && point[0] <= 150 && point[1] >= 0 && point[1] <= 150) {
            point[0] = point[0] + deltaY;
            point[1] = point[1] + deltaX;

            if (pipe.contains(point[0] * 1000 + point[1])) return true;
        }
        return false;
    }


}

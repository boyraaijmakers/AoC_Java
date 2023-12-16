package org.boy.aoc.twentytwentythree.exercises;

import java.util.HashSet;

public class day16 extends SolutionTemplate {

    public String pointOne(String input) {
        HashSet<Integer> seen = new HashSet<>();
        followLaser(input.split("\n"), -1, 1, seen, new HashSet<>());

        return String.valueOf(seen.size());
    }

    private void followLaser(String[] grid, int location, int dir, HashSet<Integer> seen, HashSet<String> seenDir) {
        boolean laserGoing = true;

        while (laserGoing) {
            location += dir;

            if(
                location < 0 ||
                (dir == -1 && (location - dir) % 1000 == 0) ||
                (location / 1000 >= grid.length) ||
                (location % 1000 >= grid[0].length()) ||
                seenDir.contains(location + ":" + dir)
            ) break;

            seen.add(location);
            seenDir.add(location + ":" + dir);

            int absDir = Math.abs(dir);

            switch (grid[location/1000].charAt(location % 1000)) {
                case '|': {
                    if(absDir == 1) {
                        followLaser(grid, location, -1000, seen, seenDir);
                        followLaser(grid, location, 1000, seen, seenDir);
                        laserGoing = false;
                    }
                    break;
                }
                case '-': {
                    if(absDir == 1000) {
                        followLaser(grid, location, -1, seen, seenDir);
                        followLaser(grid, location, 1, seen, seenDir);
                        laserGoing = false;
                    }
                    break;
                } case '/': {
                    if(absDir == 1) dir *= -1000;
                    else dir /= -1000;
                    break;
                } case '\\': {
                    if(absDir == 1) dir *= 1000;
                    else dir /= 1000;
                    break;
                }
            }
        }
    }


    public String pointTwo(String input) {
        HashSet<Integer> seen;
        String[] grid = input.split("\n");

        int max = 0;

        for(int i = 0; i < grid.length; i++) {
            seen = new HashSet<>();
            followLaser(grid, 1000 * i - 1, 1, seen, new HashSet<>());
            max = Math.max(max, seen.size());

            seen = new HashSet<>();
            followLaser(grid, 1000 * i + grid[0].length(), -1, seen, new HashSet<>());
            max = Math.max(max, seen.size());
        }

        for(int i = 0; i < grid[0].length(); i++) {
            seen = new HashSet<>();
            followLaser(grid, -1000 + i, 1000, seen, new HashSet<>());
            max = Math.max(max, seen.size());

            seen = new HashSet<>();
            followLaser(grid, 1000 * grid.length + i, -1000, seen, new HashSet<>());
            max = Math.max(max, seen.size());
        }

        return String.valueOf(max);

    }

}

package org.boy.aoc.twentytwentythree.exercises;

import java.awt.*;
import java.util.*;

public class day21 extends SolutionTemplate {

    public String pointOne(String input) {
        String[] lines = input.split("\n");
        HashSet<Point> rocks = new HashSet<>();
        HashSet<Point> currentPos = new HashSet<>();

        int size = lines.length;

        for(int i = 0; i < size; i++) {
            rocks.add(new Point(i, -1));
            rocks.add(new Point(i, size));

            rocks.add(new Point(-1, i));
            rocks.add(new Point(size, i));

            for(int j = 0; j < size; j++) {
                if(lines[i].charAt(j) == '#') rocks.add(new Point(i, j));
                else if (lines[i].charAt(j) == 'S') currentPos.add(new Point(i, j));
            }
        }

        for(int i = 0; i < 64; i++)
            currentPos = takeStep(currentPos, rocks, size);


        return String.valueOf(currentPos.size());
    }

    private HashSet<Point> takeStep(HashSet<Point> currentPos, HashSet<Point> rocks, int size) {
        HashSet<Point> nextPos = new HashSet<>();

        for(Point p: currentPos) {
            nextPos.add(new Point(p.x - 1, p.y));
            nextPos.add(new Point(p.x + 1, p.y));
            nextPos.add(new Point(p.x, p.y - 1));
            nextPos.add(new Point(p.x, p.y + 1));
        }

        nextPos.removeIf(p -> rocks.contains(new Point(goodMod(p.x, size), goodMod(p.y, size))));

        return nextPos;
    }

    private int goodMod(int a, int b) {
        return (a + b * (a/b * -1 + 1)) % b;
    }

    public String pointTwo(String input) {
        String[] lines = input.split("\n");
        HashSet<Point> rocks = new HashSet<>();
        HashSet<Point> currentPos = new HashSet<>();

        int size = lines.length;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(lines[i].charAt(j) == '#') rocks.add(new Point(i, j));
                else if (lines[i].charAt(j) == 'S') currentPos.add(new Point(i, j));
            }
        }

        // https://www.radfordmathematics.com/algebra/sequences-series/difference-method-sequences/quadratic-sequences.html
        int steps = 0;
        int[] vals = new int[3];

        while(steps < 65 + size*2) {
            steps++;
            currentPos = takeStep(currentPos, rocks, size);
            if((steps - 65) % size == 0) vals[(steps - 65) / size] = currentPos.size();
        }

        int a = (vals[2] - 2 * vals[1] + vals[0])/2;
        int b = vals[2] - vals[1] - 3*a;
        int c = vals[1] - a - b;
        long term = (26501365L - 65) / size;

        return String.valueOf(a * term * term + b * term + c);
    }

}
package org.boy.aoc.twentytwentythree.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class day14 extends SolutionTemplate {

    public String pointOne(String input) {
        String[] lines = input.split("\n");

        HashSet<Integer> walls = new HashSet<>();
        ArrayList<Integer> rocks = new ArrayList<>();

        for(int i = 1; i <= lines.length; i++) {
            walls.add(i * 1000 - i);
            walls.add(i * 1000 + lines[0].length());
        }

        for (int i = 0; i < lines[0].length(); i++) {
            walls.add(-1000 + i);
            walls.add((lines.length) * 1000 + i);
        }

        for(int i = 0; i < lines.length; i++)
            for(int j = 0; j < lines[0].length(); j++)
                switch (lines[i].charAt(j)) {
                    case 'O': rocks.add(1000 * i + j); break;
                    case '#': walls.add(1000 * i + j); break;
                }

        ArrayList<Integer> north = tilt(walls, rocks, -1000);

        int score = 0;

        for(int rock: north)
            score += lines.length - rock / 1000;


        return String.valueOf(score);
    }

    private ArrayList<Integer> tilt(HashSet<Integer> walls, ArrayList<Integer> rocks, int increment) {
        ArrayList<Integer> newRocks = new ArrayList<>();

        ArrayList<Integer> ordered = new ArrayList<>();

        if(increment > 0) for(int rock: rocks) ordered.add(ordered.size(), rock);
        else ordered = rocks;

        for(int rock: ordered) {
            while (!walls.contains(rock) && !newRocks.contains(rock)) {
                rock += increment;
            }

            newRocks.add(rock - increment);
        }

        return newRocks;
    }

    public String pointTwo(String input) {
        HashMap<HashSet<Integer>, Integer> memo = new HashMap<>();

        String[] lines = input.split("\n");

        HashSet<Integer> walls = new HashSet<>();
        ArrayList<Integer> rocks = new ArrayList<>();

        for(int i = 0; i <= lines.length; i++) {
            walls.add(i * 1000 - 1);
            walls.add(i * 1000 + lines[0].length());
        }
        print(walls);

        for (int i = 0; i < lines[0].length(); i++) {
            walls.add(-1000 + i);
            walls.add((lines.length) * 1000 + i);
        }

        print(walls);

        for(int i = 0; i < lines.length; i++)
            for(int j = 0; j < lines[0].length(); j++)
                switch (lines[i].charAt(j)) {
                    case 'O': rocks.add(1000 * i + j); break;
                    case '#': walls.add(1000 * i + j); break;
                }


        int cycle = 0;

        HashSet<Integer> state = new HashSet<>();

        while(true) {
            cycle++;
            rocks = tilt(walls, rocks, -1000);
            rocks = tilt(walls, rocks, -1);
            rocks = tilt(walls, rocks, 1000);
            rocks = tilt(walls, rocks, 1);

            state = new HashSet<>(rocks);

            print(rocks);

            if(memo.containsKey(state)) break;

            memo.put(state, cycle);
        }

        print(cycle);
        print(memo.get(state));

        int cycleLength = cycle - memo.get(state);

        int left = (1000000000 - (cycle - 1)) % cycleLength;

        for(int i = 0; i < left; i++){
            rocks = tilt(walls, rocks, -1000);
            rocks = tilt(walls, rocks, -1);
            rocks = tilt(walls, rocks, 1000);
            rocks = tilt(walls, rocks, 1);
        }

        int score = 0;
        for(int rock: rocks)
            score += lines.length - rock / 1000;


        return String.valueOf(score);
    }
}

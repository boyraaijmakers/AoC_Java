package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day5 extends SolutionTemplate {
    public String pointOne(String input) {

        String[] lines = input.split("\n\n");
        String[] seeds = lines[0].split(": ")[1].split(" ");

        HashMap<Integer, String[]> mapping = new HashMap<>();

        for (int i = 1; i < lines.length; i++) {
            String[] mapLines = lines[i].split("\n");
            mapping.put(i, Arrays.copyOfRange(mapLines, 1, mapLines.length));
        }

        long minLocation = Long.MAX_VALUE;

        for(String seed: seeds) {
            long currNum = Long.parseLong(seed);

            for (int i = 1; i < lines.length; i++) {
                String[] map = mapping.get(i);

                for (String line: map) {
                    String[] parts = line.split(" ");
                    long destStart = Long.parseLong(parts[0]);
                    long srcStart = Long.parseLong(parts[1]);
                    long range = Long.parseLong(parts[2]);

                    if(currNum >= srcStart && currNum < srcStart + range) {
                        currNum = destStart + currNum - srcStart;
                        break;
                    }
                }
            }

            minLocation = Math.min(minLocation, currNum);
        }

        return String.valueOf(minLocation);
    }

    public String pointTwo(String input) {
        // Should be possible faster, currently takes 7 minutes.
        String[] lines = input.split("\n\n");
        String[] seeds = lines[0].split(": ")[1].split(" ");

        HashMap<Integer, String[]> mapping = new HashMap<>();

        for (int i = 1; i < lines.length; i++) {
            String[] mapLines = lines[i].split("\n");
            mapping.put(i, Arrays.copyOfRange(mapLines, 1, mapLines.length));
        }

        ArrayList<long[]> seedRanges = new ArrayList<>();

        for(int i = 0; i < seeds.length; i+=2)
            seedRanges.add(new long[] {Long.parseLong(seeds[i]), Long.parseLong(seeds[i+1])});

        long location = 1;

        while(true) {
            long currNum = location;

            for (int i = lines.length - 1; i > 0; i--) {
                String[] map = mapping.get(i);

                for (String line: map) {
                    String[] parts = line.split(" ");
                    long destStart = Long.parseLong(parts[0]);
                    long srcStart = Long.parseLong(parts[1]);
                    long range = Long.parseLong(parts[2]);

                    if(currNum >= destStart && currNum < destStart + range) {
                        currNum = srcStart + currNum - destStart;
                        break;
                    }
                }
            }

            for(long[] range: seedRanges)
                if (currNum >= range[0] && currNum < range[0] + range[1])
                    return String.valueOf(location);

            location += 1;
        }
    }

}

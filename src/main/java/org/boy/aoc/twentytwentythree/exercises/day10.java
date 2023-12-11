package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashSet;

public class day10 extends SolutionTemplate {
    public String pointOne(String input) {
        return String.valueOf(parsePipe(input).size() / 2);
    }

    public String pointTwo(String input) {
        HashSet<Integer> pipe = parsePipe(input);

        input = input.replace("S", "L");
        String[] lines = input.split("\n");

        int insides = 0;

        for(int i = 0; i < lines.length; i++) {
            boolean inPipe = false;
            char lastBend = '.';
            for (int j = 0; j < lines[0].length(); j++) {
                if(pipe.contains(i * 1000 + j)) {
                    char pipeChar = lines[i].charAt(j);

                    if(pipeChar == '|' || (pipeChar == 'J' && lastBend == 'F') || (pipeChar == '7' && lastBend == 'L'))
                        inPipe = !inPipe;
                    else if(pipeChar == 'L' || pipeChar == 'F' || pipeChar == 'J' || pipeChar == '7')
                        lastBend = pipeChar;

                } else if (inPipe) {
                    insides++;
                }
            }
        }

        return String.valueOf(insides);
    }

    private HashSet<Integer> parsePipe(String input) {
        String[] lines = input.split("\n");

        int[] start = new int[2];

        for(int i = 0; i < lines.length; i++)
            for (int j = 0; j < lines[0].length(); j++)
                if(lines[i].charAt(j) == 'S') start = new int[] {i, j};

        int dir = 1;
        int[] current = new int[] {start[0] - 1, start[1]};

        HashSet <Integer> pipe = new HashSet<>();
        pipe.add(start[0] * 1000 + start[1]);

        // 1 UP, 2 RIGHT, 3 DOWN, 4 LEFT

        while (!pipe.contains(current[0] * 1000 + current[1])) {
            pipe.add(current[0] * 1000 + current[1]);

            switch (lines[current[0]].charAt(current[1])) {
                case '|': {
                    current[0] = current[0] + (dir == 1 ? -1 : 1);
                    break;
                }
                case '-': {
                    current[1] = current[1] + (dir == 2 ? 1 : -1);
                    break;
                }
                case 'L': {
                    current[0] = current[0] + (dir == 4 ? -1 : 0);
                    current[1] = current[1] + (dir == 3 ? 1 : 0);
                    dir = dir == 3 ? 2 : 1;
                    break;
                }
                case 'J': {
                    current[0] = current[0] + (dir == 2 ? -1 : 0);
                    current[1] = current[1] + (dir == 3 ? -1 : 0);
                    dir = dir == 2 ? 1 : 4;
                    break;
                }
                case '7': {
                    current[0] = current[0] + (dir == 2 ? 1 : 0);
                    current[1] = current[1] + (dir == 1 ? -1 : 0);
                    dir = dir == 1 ? 4 : 3;
                    break;
                }
                case 'F': {
                    current[0] = current[0] + (dir == 4 ? 1 : 0);
                    current[1] = current[1] + (dir == 1 ? 1 : 0);
                    dir = dir == 1 ? 2 : 3;
                    break;
                }
            }
        }

        return pipe;
    }
}

package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class day13 extends SolutionTemplate {

    public String pointOne(String input) {
        String[] blocks = input.split("\n\n");

        int sum = 0;

        for (String block: blocks) {
            String[] lines = block.split("\n");

            for(int i = 0; i < lines.length-1; i++) {
                if(lines[i].equals(lines[i+1])) {
                    int back = i, forward = i + 1;
                    boolean found = true;
                    while(back >= 0 && forward < lines.length) {
                        if(!lines[back].equals(lines[forward])) found = false;

                        back--;
                        forward++;
                    }

                    if(found) {
                        sum += 100 * (i+1);
                        break;
                    }
                }
            }

            String[] pivotted = pivotString(lines);

            for(int i = 0; i < pivotted.length-1; i++) {
                if(pivotted[i].equals(pivotted[i+1])) {
                    int back = i, forward = i + 1;
                    boolean found = true;
                    while(back >= 0 && forward < pivotted.length) {
                        if(!pivotted[back].equals(pivotted[forward])) found = false;

                        back--;
                        forward++;
                    }

                    if(found) {
                        sum += (i+1);
                        break;
                    }
                }
            }

        }
        return String.valueOf(sum);
    }

    private String[] pivotString(String[] input) {
        String[] output = new String[input[0].length()];

        for (int i = 0; i < input[0].length(); i++) {
            StringBuilder el = new StringBuilder();
            for(String line: input)
                el.append(line.charAt(i));

            output[i] = el.toString();
        }

        return output;
    }

    public String pointTwo(String input) {
        String[] blocks = input.split("\n\n");

        int sum = 0;

        for (String block: blocks) {

            int normal = 0;
            char colRow = 'R';

            String[] lines = block.split("\n");

            for(int i = 0; i < lines.length-1; i++) {
                if(lines[i].equals(lines[i+1])) {
                    int back = i, forward = i + 1;
                    boolean found = true;
                    while(back >= 0 && forward < lines.length) {
                        if(!lines[back].equals(lines[forward])) found = false;

                        back--;
                        forward++;
                    }

                    if(found) {
                        normal = i;
                        colRow = 'R';
                        break;
                    }
                }
            }

            String[] pivotted = pivotString(lines);

            for(int i = 0; i < pivotted.length-1; i++) {
                if(pivotted[i].equals(pivotted[i+1])) {
                    int back = i, forward = i + 1;
                    boolean found = true;
                    while(back >= 0 && forward < pivotted.length) {
                        if(!pivotted[back].equals(pivotted[forward])) found = false;

                        back--;
                        forward++;
                    }

                    if(found) {
                        normal = i;
                        colRow = 'C';
                        break;
                    }
                }
            }

            boolean done = false;

            for(int x = 0; x < lines[0].length(); x++) {

                if(done) break;

                for(int y = 0; y < lines.length; y++) {
                    if(done) break;


                    lines = block.split("\n");

                    StringBuilder s = new StringBuilder(lines[y]);
                    s.setCharAt(x, s.charAt(x) == '#' ? '.' : '#');

                    lines[y] = s.toString();


                    for(int i = 0; i < lines.length - 1; i++) {
                        if(lines[i].equals(lines[i+1])) {
                            int back = i, forward = i + 1;
                            boolean found = true;
                            while(back >= 0 && forward < lines.length) {
                                if(!lines[back].equals(lines[forward])) found = false;

                                back--;
                                forward++;
                            }

                            if(found) {

                                if(i != normal || colRow != 'R') {
                                    sum += 100 * (i + 1);
                                    done = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(done) break;

                    pivotted = pivotString(lines);

                    for(int i = 0; i < pivotted.length - 1; i++) {
                        if(pivotted[i].equals(pivotted[i+1])) {
                            int back = i, forward = i + 1;
                            boolean found = true;
                            while(back >= 0 && forward < pivotted.length) {
                                if (!pivotted[back].equals(pivotted[forward])) {
                                    found = false;
                                    break;
                                }

                                back--;
                                forward++;
                            }

                            if(found) {
                                if(i != normal || colRow != 'C') {
                                    sum += (i + 1);
                                    done = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }

        return String.valueOf(sum);
    }

}

package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day18 extends SolutionTemplate {

    public String pointOne(String input) {
        return solve(input, false);
    }

    public String pointTwo(String input) {
        return solve(input, true);
    }

    private String solve(String input, boolean part2){
        String[] lines = input.split("\n");

        long currentX = 0, currentY = 0, borderSize = 0, deltaX, deltaY;
        ArrayList<String> corners = new ArrayList<>();

        for(String line: lines) {
            String[] parts = line.split(" | \\(|\\)");
            String dir;
            long steps;

            if(part2) {
                String op = parts[2];

                dir = switch (op.charAt(7)) {
                    case '0' -> "R";
                    case '1' -> "D";
                    case '2' -> "L";
                    case '3' -> "U";
                    default -> "";
                };
                steps = Integer.parseInt(op.substring(2, 7), 16);
            } else {
                dir = parts[0];
                steps = Integer.parseInt(parts[1]);
            }

            borderSize += steps;

            deltaX = switch (dir) {
                case "L" -> -1;
                case "R" -> 1;
                default -> 0;
            };

            deltaY = switch (dir) {
                case "U" -> -1;
                case "D" -> 1;
                default -> 0;
            };

            currentX += deltaX * steps;
            currentY += deltaY * steps;

            corners.add(currentX + ":" + currentY);
        }

        return String.valueOf(shoelace(corners) + borderSize/2 + 1);
    }

    private long shoelace(List<String> corners) {
        long area = 0L;
        for(int i = 0; i < corners.size(); i++) {
            String[] a = corners.get(i).split(":");
            String[] b = corners.get((i+1) % corners.size()).split(":");

            area += Long.parseLong(a[0]) * Long.parseLong(b[1]) - Long.parseLong(a[1]) * Long.parseLong(b[0]);
        }
        return Math.abs(area) / 2;
    }
}

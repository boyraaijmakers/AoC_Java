package org.boy.aoc.twentytwentyone.exercises;

import java.util.HashMap;

public class day5 extends SolutionTemplate {
    public String pointOne(String input) {
        return String.valueOf(getOverlapCount(input, false));
    }

    public String pointTwo(String input) {
        return String.valueOf(getOverlapCount(input, true));
    }

    private int getOverlapCount(String input, boolean includeDiagonal) {
        HashMap<String, Integer> counts = new HashMap<>();

        for (String line: input.split("\n")) {
            String[] elements = line.split(" -> ");
            Point start = new Point(elements[0]);
            Point end = new Point(elements[1]);

            if (start.x == end.x) {
                for (int i = Math.min(start.y, end.y); i <= Math.max(start.y, end.y); i++) {
                    String coord = String.format("%s,%s", start.x, i);

                    if (!counts.containsKey(coord))
                        counts.put(coord, 0);

                    counts.put(coord, counts.get(coord) + 1);
                }
            }
            else if (start.y == end.y) {
                for (int i = Math.min(start.x, end.x); i <= Math.max(start.x, end.x); i++) {
                    String coord = String.format("%s,%s", i, start.y);

                    if (!counts.containsKey(coord))
                        counts.put(coord, 0);

                    counts.put(coord, counts.get(coord) + 1);
                }
            } else if (includeDiagonal) {
                int min_x = Math.min(start.x, end.x);
                int max_x = Math.max(start.x, end.x);

                for (int i = 0; i <= max_x - min_x; i++) {
                    String coord;

                    if (start.x > end.x) {
                        if (start.y > end.y) coord = String.format("%s,%s", start.x - i, start.y - i);
                        else coord = String.format("%s,%s", start.x - i, start.y + i);
                    } else {
                        if (start.y > end.y) coord = String.format("%s,%s", start.x + i, start.y - i);
                        else coord = String.format("%s,%s", start.x + i, start.y + i);
                    }

                    if (!counts.containsKey(coord))
                        counts.put(coord, 0);

                    counts.put(coord, counts.get(coord) + 1);
                }
            }
        }

        int doubles = 0;
        for (int value: counts.values()) {
            doubles += value > 1 ? 1 : 0;
        }

        return doubles;
    }

    private static class Point {
        public int x;
        public int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point (String coords) {
            this.x = Integer.parseInt(coords.split(",")[0]);
            this.y = Integer.parseInt(coords.split(",")[1]);
        }

        public String toString() {
            return String.format("(%s, %s)", this.x, this.y);
        }
    }
}



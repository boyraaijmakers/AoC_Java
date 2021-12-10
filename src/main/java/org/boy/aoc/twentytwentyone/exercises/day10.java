package org.boy.aoc.twentytwentyone.exercises;


import java.util.*;

public class day10 extends SolutionTemplate {

    private final HashMap<Character, Integer> openingMapping = new HashMap<>();
    private final HashMap<Character, Integer> closingMapping = new HashMap<>();
    private final HashMap<Character, Integer> corruptedScore = new HashMap<>();

    public day10() {
        String opening = "([{<";
        String closing = ")]}>";
        for (int i = 1; i < opening.length() + 1; i++) {
            openingMapping.put(opening.charAt(i-1), i);
            closingMapping.put(closing.charAt(i-1), i);
        }

        corruptedScore.put(')', 3);
        corruptedScore.put(']', 57);
        corruptedScore.put('}', 1197);
        corruptedScore.put('>', 25137);
    }

    public String pointOne(String input) {
        long total = 0;
        for(String line: input.split("\n")) {
            total += getCorruptedScore(line, false);
        }
        return String.valueOf(total);
    }

    public String pointTwo(String input) {
        ArrayList<Long> scores = new ArrayList<>();
        for(String line: input.split("\n")) {
            long score = getCorruptedScore(line, true);
            if (score >= 0) scores.add(score);
        }
        Collections.sort(scores);
        return String.valueOf(scores.get((scores.size() / 2)));
    }

    private long getCorruptedScore(String line, boolean finishSignal) {
        Stack<Integer> openingChars = new Stack<>();
        boolean corrupted = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            switch (c) {
                case '[':
                case '{':
                case '(':
                case '<':
                    openingChars.push(this.openingMapping.get(c)); break;
                case '}':
                case ')':
                case ']':
                case '>': {
                    if (openingChars.pop() != this.closingMapping.get(c)) {
                        corrupted = true;
                        if (!finishSignal) return (long) this.corruptedScore.get(c);
                    }
                }
            }
        }

        if (!finishSignal) return 0L;
        if (corrupted) return -1L;

        long score = 0;
        while (!openingChars.isEmpty())
            score = score * 5 + openingChars.pop();

        return score;
    }


}



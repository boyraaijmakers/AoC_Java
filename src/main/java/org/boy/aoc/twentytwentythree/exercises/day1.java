package org.boy.aoc.twentytwentythree.exercises;

import org.boy.aoc.twentytwentythree.exercises.SolutionTemplate;

import java.util.Arrays;

public class day1 extends SolutionTemplate {
    public String pointOne(String input) {
        return Arrays.stream(input.split("\n"))
                .map(s -> mapString(s, false))
                .reduce(Integer::sum).orElse(-1).toString();
    }

    public String pointTwo(String input) {
        return Arrays.stream(input.split("\n"))
                .map(s -> mapString(s, true))
                .reduce(Integer::sum).orElse(-1).toString();
    }

    private int mapString(String s, boolean includeWords) {
        StringBuilder digits = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if(i <= s.length() - 3 && includeWords)
                switch (s.substring(i, i + 3)) {
                    case "one": digits.append("1"); break;
                    case "two": digits.append("2"); break;
                    case "six": digits.append("6"); break;
                }

            if(i <= s.length() - 4 && includeWords)
                switch (s.substring(i, i + 4)){
                    case "four": digits.append("4"); break;
                    case "five": digits.append("5"); break;
                    case "nine": digits.append("9");
                }

            if(i <= s.length() - 5 && includeWords)
                switch (s.substring(i, i + 5)){
                    case "three": digits.append("3"); break;
                    case "seven": digits.append("7"); break;
                    case "eight": digits.append("8");
                }

            if(Character.isDigit(s.charAt(i)))
                digits.append(s.charAt(i));
        }

        return Integer.parseInt(String.valueOf(digits.charAt(0)) + digits.charAt(digits.length() - 1));
    }
}

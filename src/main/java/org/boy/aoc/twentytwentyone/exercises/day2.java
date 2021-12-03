package org.boy.aoc.twentytwentyone.exercises;

public class day2 extends SolutionTemplate {
    public String pointOne(String input) {
        String[] inputArray = input.split("\n");

        int horizontal = 0;
        int depth = 0;

        for (int i = 0; i < inputArray.length; i++) {
            String[] line = inputArray[i].split(" ");
            int value = Integer.parseInt(line[1]);
            switch (line[0]) {
                case "forward": horizontal += value; break;
                case "up": depth -= value; break;
                case "down": depth += value; break;
                default: System.out.println("Faulty input");
            }
        }

        return String.valueOf(horizontal * depth);
    }

    public String pointTwo(String input) {
        String[] inputArray = input.split("\n");

        int aim = 0;
        int horizontal = 0;
        int depth = 0;

        for (int i = 0; i < inputArray.length; i++) {
            String[] line = inputArray[i].split(" ");
            int value = Integer.parseInt(line[1]);
            switch (line[0]) {
                case "forward": horizontal += value; depth += value * aim; break;
                case "up": aim -= value; break;
                case "down": aim += value; break;
                default: System.out.println("Faulty input");
            }
        }

        return String.valueOf(horizontal * depth);
    }

}

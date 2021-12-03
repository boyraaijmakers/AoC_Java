package org.boy.aoc.twentytwentyone.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class day3 extends SolutionTemplate {
    public String pointOne(String input) {
        ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(input.split("\n")));

        String gamma = "";
        String epsilon = "";

        for (int i = 0; i <inputArray.get(0).length(); i++) {
            char mostCommon = getMostCommon(inputArray, i, '1');

            gamma += mostCommon;
            epsilon += mostCommon == '1' ? '0': '1';
        }

        return String.valueOf(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));
    }

    public String pointTwo(String input) {
        ArrayList<String> leftOverOne = new ArrayList<>(Arrays.asList(input.split("\n")));
        ArrayList<String> leftOverTwo = new ArrayList<>(leftOverOne);

        int i = 0;
        while (leftOverOne.size() > 1) {
            ArrayList<String> next = new ArrayList<>();
            for (String item: leftOverOne)
                if (item.charAt(i) == getMostCommon(leftOverOne, i, '1'))
                    next.add(item);

            i++;
            leftOverOne = new ArrayList<>(next);
        }

        i = 0;
        while (leftOverTwo.size() > 1) {
            ArrayList<String> next = new ArrayList<>();
            for (String item: leftOverTwo)
                if (item.charAt(i) != getMostCommon(leftOverTwo, i, '0'))
                    next.add(item);

            i++;
            leftOverTwo = new ArrayList<>(next);
        }

        return String.valueOf(
                Integer.parseInt(leftOverOne.get(0), 2) * Integer.parseInt(leftOverTwo.get(0), 2)
        );
    }

    private char getMostCommon(ArrayList<String> input, int position, char preferred) {
        int countOnes = 0;

        for (String s : input)
            if (s.charAt(position) == '1')
                countOnes++;

        return countOnes * 2 >= input.size() ? '1' : '0';
    }
}



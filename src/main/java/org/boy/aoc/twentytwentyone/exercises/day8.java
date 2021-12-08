package org.boy.aoc.twentytwentyone.exercises;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class day8 extends SolutionTemplate {

    public String pointOne(String input) {
        return String.valueOf(Arrays.stream(input.split("\n")).mapToInt(line ->
            Arrays.stream(line.split("\\|")[1].split(" ")).mapToInt(x ->
                    x.length() == 2 || x.length() == 3 || x.length() == 4 || x.length() == 7 ? 1 : 0
            ).sum()
        ).sum());
    }

    public String pointTwo(String input) {
        int total = 0;
        for (String line: input.split("\n")) {
            String[] split = line.split(" \\| ");
            Map<Integer, String> mapping = getMapping(split[0]);
            String numberStr = "";

            for (String signal: split[1].split(" "))
                numberStr += mapping.get(getStringEncoded(signal));

            total += Integer.parseInt(numberStr);
        }
        return String.valueOf(total);
    }

    private Map<Integer, String> getMapping(String pattern) {
        Map<String, String> mapping = new HashMap<>();
        ArrayList<String> signals = new ArrayList<>(List.of(pattern.split(" ")));
        ArrayList<String> remaining = new ArrayList<>(List.of(pattern.split(" ")));

        // Find 1, 4, 7, 8
        for (String signal: signals) {
            switch (signal.length()) {
                case 2: mapping.put("1", signal); remaining.remove(signal); break;
                case 3: mapping.put("7", signal); remaining.remove(signal); break;
                case 4: mapping.put("4", signal); remaining.remove(signal); break;
                case 7: mapping.put("8", signal); remaining.remove(signal);
            }
        }

        Set<String> charsEight = new HashSet<>(Arrays.asList(mapping.get("8").split("")));
        Set<String> charsOne = new HashSet<>(Arrays.asList(mapping.get("1").split("")));
        Set<String> charsFour = new HashSet<>(Arrays.asList(mapping.get("4").split("")));
        signals = new ArrayList<>(remaining);

        // Find 6
        for (String signal: signals) {
            if (signal.length() == 6) {
                Set<String> charsNumber = new HashSet<>(Arrays.asList(signal.split("")));
                charsNumber.addAll(charsOne);
                if (charsNumber.equals(charsEight)) {
                    mapping.put("6", signal);
                    remaining.remove(signal);
                }
            }
        }

        signals = new ArrayList<>(remaining);

        // Find 0
        for (String signal: signals) {
            if (signal.length() == 6) {
                Set<String> charsNumber = new HashSet<>(Arrays.asList(signal.split("")));
                charsNumber.addAll(charsFour);
                if (charsNumber.equals(charsEight)) {
                    mapping.put("0", signal);
                    remaining.remove(signal);
                }
            }
        }

        signals = new ArrayList<>(remaining);

        // Find 9
        for (String signal: signals) {
            if (signal.length() == 6) {
                mapping.put("9", signal);
                remaining.remove(signal);
            }
        }

        signals = new ArrayList<>(remaining);
        Set<String> charsNine = new HashSet<>(Arrays.asList(mapping.get("9").split("")));

        // Find 5
        for (String signal: signals) {
            Set<String> charsNumber = new HashSet<>(Arrays.asList(signal.split("")));
            charsNumber.addAll(charsOne);
            if (charsNumber.equals(charsNine)) {
                mapping.put("5", signal);
                remaining.remove(signal);
            }
        }

        signals = new ArrayList<>(remaining);

        // Find 2
        for (String signal: signals) {
            Set<String> charsNumber = new HashSet<>(Arrays.asList(signal.split("")));
            charsNumber.addAll(charsFour);
            if (charsNumber.equals(charsEight)) {
                mapping.put("2", signal);
                remaining.remove(signal);
            }
        }

        // Push 3
        mapping.put("3", remaining.get(0));

        Map<Integer, String> mappingFinal = new HashMap<>();
        for (String value: mapping.keySet())
            mappingFinal.put(getStringEncoded(mapping.get(value)), value);

        return mappingFinal;
    }

    private int getStringEncoded (String input) {
        Map<Character, Integer> map = Stream.of(new String[][] {
                { "a", "1" },
                { "b", "10" },
                { "c", "100" },
                { "d", "1000" },
                { "e", "10000" },
                { "f", "100000" },
                { "g", "1000000" }
        }).collect(Collectors.toMap(data -> data[0].charAt(0), data -> Integer.parseInt(data[1])));

        int encoded = 0;

        for (int i = 0; i < input.length(); i++)
            encoded += map.get(input.charAt(i));

        return encoded;
    }
}



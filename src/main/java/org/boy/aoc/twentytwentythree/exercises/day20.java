package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day20 extends SolutionTemplate {

    public String pointOne(String input) {
        HashMap<String, ArrayList<String>> mapping = new HashMap<>();
        HashMap<String, HashMap<String, Character>> cons = new HashMap<>();
        HashMap<String, Boolean> flips = new HashMap<>();

        for(String line: input.split("\n")) {
            String[] parts = line.split(" -> ");
            String key = parts[0].substring(1);

            ArrayList<String> tos = new ArrayList<>(List.of(parts[1].split(", ")));

            mapping.put(key, tos);

            if(parts[0].charAt(0) == '&')
                cons.put(key, new HashMap<>());

            if(parts[0].charAt(0) == '%')
                flips.put(key, false);
        }

        for(String key: mapping.keySet())
            for(String val: mapping.get(key))
                if(cons.containsKey(val)) cons.get(val).put(key, '-');

        long high = 0;
        long low = 0;



        for(int i=0; i<1000; i++) {
            long res = sendSignal(mapping, cons, flips);
            high += res / 1000;
            low += res % 1000;
        }

        return String.valueOf(high * low);
    }

    private long sendSignal(HashMap<String, ArrayList<String>> mapping,
                           HashMap<String, HashMap<String, Character>> cons,
                           HashMap<String, Boolean> flips) {

        LinkedList<String> todo = new LinkedList<>();
        long high = 0L;
        long low = 1L;

        for(String next: mapping.get("roadcaster")) {
            todo.add("roadcaster -" + next);
        }

        while(!todo.isEmpty()) {

            String newTodo = todo.pop();
            String[] props = newTodo.split(" ");

            String previous = props[0];
            char signal = props[1].charAt(0);
            String current = props[1].substring(1);

            if(signal == '-') low++;
            else high++;

            if(cons.containsKey(current)) {
                cons.get(current).put(previous, signal);
                boolean allHigh = true;
                for(char c: cons.get(current).values())
                    allHigh &= c == '+';

                for(String next: mapping.get(current)) {
                    todo.add(current + " " + (allHigh ? "-" : "+") + next);
                }
            }
            else if (flips.containsKey(current) && signal == '-') {
                flips.put(current, !flips.get(current));

                for(String next: mapping.get(current)) {
                    todo.add(current + " " + (flips.get(current) ? "+" : "-") + next);
                }
            }
        }

        return high * 1000 + low;
    }


    public String pointTwo(String input) {
        HashMap<String, ArrayList<String>> mapping = new HashMap<>();
        HashMap<String, HashMap<String, Character>> cons = new HashMap<>();
        HashMap<String, Boolean> flips = new HashMap<>();

        for(String line: input.split("\n")) {
            String[] parts = line.split(" -> ");
            String key = parts[0].substring(1);

            ArrayList<String> tos = new ArrayList<>(List.of(parts[1].split(", ")));

            mapping.put(key, tos);

            if(parts[0].charAt(0) == '&')
                cons.put(key, new HashMap<>());

            if(parts[0].charAt(0) == '%')
                flips.put(key, false);
        }

        for(String key: mapping.keySet())
            for(String val: mapping.get(key))
                if(cons.containsKey(val)) cons.get(val).put(key, '-');

        long fullLCM = 1L;
        HashSet<String> toFind = new HashSet<>(cons.get("rm").keySet());

        int count = 0;

        while(!toFind.isEmpty()) {
            count++;
            sendSignal(mapping, cons, flips);
            HashMap<String, Character> checks = cons.get("rm");

            for(String check: checks.keySet()) {
                if(checks.get(check) == '+') {
                    toFind.remove(check);
                    fullLCM = lcm(fullLCM, count);
                }
            }
        }

        return String.valueOf(fullLCM);
    }

    private static long lcm(long number1, long number2) {
        long absHigherNumber = Math.max(number1, number2);
        long absLowerNumber = Math.min(number1, number2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

}
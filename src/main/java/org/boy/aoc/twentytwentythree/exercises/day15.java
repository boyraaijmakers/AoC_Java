package org.boy.aoc.twentytwentythree.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class day15 extends SolutionTemplate {

    public String pointOne(String input) {

        return Arrays.stream(input.split(","))
                .map(day15::hash)
                .reduce(Integer::sum)
                .orElse(-1)
                .toString();
    }

    private static int hash(String line) {
        int currentValue = 0;

        for(char c: line.toCharArray())
            currentValue = ((currentValue + c) * 17) % 256;

        return currentValue;
    }

    public String pointTwo(String input) {
        String[] ops = input.split(",");

        HashMap<Integer, ArrayList<String>> mapping = new HashMap<>();

        for (String op: ops) {
            boolean rm = op.charAt(op.length() - 1) == '-';

            String label = (rm) ? op.substring(0, op.length() - 1) : op.split("=")[0];
            int box = hash(label);

            ArrayList<String> lenses = mapping.containsKey(box) ? mapping.get(box) : new ArrayList<>();

            if(rm) {
                String found = "";
                for(String lens: lenses)
                    if(lens.split("=")[0].equals(label))
                        found = lens;

                if(!found.isEmpty())
                    lenses.remove(found);
            }
            else {
                boolean found = false;
                for(int i = 0; i < lenses.size(); i++)
                    if(lenses.get(i).split("=")[0].equals(label)) {
                        found = true;
                        lenses.remove(i);
                        lenses.add(i, op);
                    }

                if(!found) lenses.add(op);
            }

            mapping.put(box, lenses);
        }

        int sum = 0;

        for(int box: mapping.keySet()) {
            ArrayList<String> res = mapping.get(box);
            for(int i = 0; i < res.size(); i++)
                sum += (box+1) * (i+1) * Integer.parseInt(res.get(i).split("=")[1]);
        }

        return String.valueOf(sum);
    }

}

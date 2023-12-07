package org.boy.aoc.twentytwentythree.exercises;

import java.util.*;

public class day7 extends SolutionTemplate {
    public String pointOne(String input) {
        return solve(input, false);
    }

    public String pointTwo(String input) {
        return solve(input, true);
    }

    private String solve(String input, boolean part2) {
        HashMap<String, Integer> mapping = new HashMap<>();
        List<String> keyList = new ArrayList<>();

        for(String line: input.split("\n")) {
            String[] parts = line.split(" ");
            mapping.put(parts[0], Integer.parseInt(parts[1]));
            keyList.add(parts[0]);
        }

        keyList.sort(new SortCards(part2));

        long sum = 0L;

        for(int i = 0; i < keyList.size(); i++)
            sum += (long) mapping.get(keyList.get(i)) * (i + 1);

        return String.valueOf(sum);
    }


    private record SortCards(boolean part2) implements Comparator<String> {

        public int compare(String a, String b) {
                int scoreA = getType(a);
                int scoreB = getType(b);

                if (scoreA != scoreB) return scoreB - scoreA;

                List<Character> ordering;

                if (this.part2) ordering = Arrays.asList('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');
                else ordering = Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');

                for (int i = 0; i < a.length(); i++) {
                    if (a.charAt(i) == b.charAt(i)) continue;
                    return ordering.indexOf(b.charAt(i)) - ordering.indexOf(a.charAt(i));
                }

                return 0;
            }

            private int getType(String a) {
                HashMap<Character, Integer> counts = new HashMap<>();
                counts.put('J', 0);

                int max = 0;
                int maxMinOne = 0;

                for (char c : a.toCharArray()) {
                    if (counts.containsKey(c)) counts.put(c, counts.get(c) + 1);
                    else counts.put(c, 1);

                    if ((!this.part2 || c != 'J') && counts.get(c) > max)
                        max = counts.get(c);
                    else if ((!this.part2 || c != 'J') && counts.get(c) > maxMinOne)
                        maxMinOne = counts.get(c);
                }

                if (this.part2) max += counts.get('J');

                return switch (max) {
                    case 5 -> 1;
                    case 4 -> 2;
                    case 3 -> maxMinOne >= 2 ? 3 : 4;
                    case 2 -> maxMinOne == 2 ? 5 : 6;
                    default -> 7;
                };
            }
        }



}

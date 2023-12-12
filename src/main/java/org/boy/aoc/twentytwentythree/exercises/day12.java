package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashMap;

public class day12 extends SolutionTemplate {

    public String pointOne(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> getCount(line, false))
                .reduce(Long::sum)
                .orElse(-1L)
                .toString();
    }

    public String pointTwo(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> getCount(line, true))
                .reduce(Long::sum)
                .orElse(-1L)
                .toString();
    }

    private static long getCount(String line, boolean second) {
        String[] parts = line.split(" ");

        if(second) {
            parts[0] = parts[0] + "?" + parts[0] + "?" + parts[0] + "?" + parts[0] + "?" + parts[0];
            parts[1] = parts[1] + "," + parts[1] + "," + parts[1] + "," + parts[1] + "," + parts[1];
        }

        HashMap<String, Long> memo = new HashMap<>();

        int[] nums = Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();

        return getCombs(parts[0] + ".", nums, memo);
    }

    private static long getCombs(String pattern, int[] nums, HashMap<String, Long> memo) {
        if (nums.length == 0)
            return pattern.indexOf('#') == -1 ? 1 : 0;

        long combs = 0;
        int num = nums[0];

        for(int i = 0; i <= pattern.length() - num; i++)
            if(checkPos(pattern, num, i)) {
                String newPattern = pattern.substring(i + num + 1);
                int[] newNums = Arrays.copyOfRange(nums, 1, nums.length);

                String id = getId(newPattern, newNums);

                if(memo.containsKey(id))
                    combs += memo.get(id);
                else {
                    long c = getCombs(newPattern, newNums, memo);
                    combs += c;
                    memo.put(id, c);
                }
            }

        return combs;
    }

    private static boolean checkPos(String pattern, int length, int start) {
        for(int i = 0; i < start; i++)
            if(pattern.charAt(i) == '#')
                return false;

        for (int i = 0; i < length; i++)
            if (pattern.charAt(start + i) == '.')
                return false;

        return pattern.length() <= start + length || pattern.charAt(start + length) != '#';
    }

    private static String getId (String pattern, int[] todo) {
        StringBuilder patternBuilder = new StringBuilder(pattern);
        for(int i: todo) patternBuilder.append(i).append(",");
        return patternBuilder.toString();
    }

}

package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;

public class day12 extends SolutionTemplate {
    public String pointOne(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> getCount(line, false))
                .reduce(Long::sum)
                .toString();
    }

    public String pointTwo(String input) {
        // Highly inefficient, but eventually gets there..
        return Arrays.stream(input.split("\n"))
                .map(line -> getCount(line, true))
                .reduce(Long::sum)
                .toString();
    }

    private static long getCount(String line, boolean second) {
        String[] parts = line.split(" ");

        if(second) {
            parts[0] = parts[0] + "?" + parts[0] + "?" + parts[1] + "?" + parts[0] + "?" + parts[0];
            parts[1] = parts[1] + "," + parts[1] + "," + parts[1] + "," + parts[1] + "," + parts[1];
        }

        int[] nums = Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();
        return getCombs(parts[0] + ".", nums);
    }

    private static long getCombs(String pattern, int[] nums) {
        if (nums.length == 0)
            return pattern.indexOf('#') == -1 ? 1 : 0;

        long combs = 0;
        int num = nums[0];

        for(int i = 0; i <= pattern.length() - num; i++)
            if(checkPos(pattern, num, i))
                combs += getCombs(
                        pattern.substring(i + num + 1),
                        Arrays.copyOfRange(nums, 1, nums.length)
                );

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

}

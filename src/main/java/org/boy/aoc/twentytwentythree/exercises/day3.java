package org.boy.aoc.twentytwentythree.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class day3 extends SolutionTemplate {
    public String pointOne(String input) {
        int sum = 0;
        String[] inputLines = input.split("\n");

        for(int i = 0; i < inputLines.length; i++)
            for(int j = 0; j < inputLines[0].length(); j++)
                if(Character.isDigit(inputLines[i].charAt(j))) {
                    String numStr = getNumString(inputLines[i], j, false);

                    if(checkValid(inputLines, i, j, j + numStr.length()))
                        sum += Integer.parseInt(numStr);

                    j += numStr.length();
                }

        return String.valueOf(sum);
    }

    public String pointTwo(String input) {
        int sum = 0;
        String[] inputLines = input.split("\n");

        for(int i = 1; i < inputLines.length - 1; i++)
            for(int j = 1; j < inputLines[0].length() - 1; j++)
                if(inputLines[i].charAt(j) == '*')
                    sum += checkAndParseStart(inputLines, i, j);

        return String.valueOf(sum);
    }

    private String getNumString(String line, int start, boolean searching) {
        if (searching && start > 0 && Character.isDigit(line.charAt(start - 1)))
            return getNumString(line, start - 1, true);

        if (start < 0 || start == line.length() || !Character.isDigit(line.charAt(start)))
            return "";

        return line.charAt(start) + getNumString(line, start + 1, false);
    }

    private boolean checkValid (String[] lines, int lineNum, int start, int end) {

        for (int i = lineNum - 1; i <= lineNum + 1; i++) {
            if(i < 0 || i >= lines.length) continue;

            for(int j = start - 1; j <= end; j++) {
                if(j < 0 || j >= lines[i].length()) continue;
                char check = lines[i].charAt(j);
                if(!Character.isDigit(check) && check != '.') return true;
            }
        }

        return false;
    }

    private int checkAndParseStart (String[] lines, int lineNum, int pos) {
        HashSet<Integer> numsFound = new HashSet<>();
        for (int k = lineNum - 1; k <= lineNum + 1; k++)
            for (int l = pos - 1; l <= pos + 1; l++)
                if(Character.isDigit(lines[k].charAt(l)))
                    numsFound.add(Integer.parseInt(getNumString(lines[k], l, true)));

        return numsFound.size() == 2 ? numsFound.stream().reduce(Math::multiplyExact).orElse(0) : 0;
    }
}

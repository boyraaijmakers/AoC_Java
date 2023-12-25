package org.boy.aoc.twentytwentythree.exercises;

import java.util.ArrayList;

public class day24 extends SolutionTemplate {

    public String pointOne(String input) {
        ArrayList<double[]> parsed = new ArrayList<>();

        for(String line: input.split("\n")) {
            String[] split = line.split(", | @ ");
            parsed.add(new double[] {
                Double.parseDouble(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[3]),
                Double.parseDouble(split[4])
            });
        }

        int sum = 0;
        long minBound = 200000000000000L;
        long maxBound = 400000000000000L;

        for(int i = 0; i < parsed.size() - 1; i++)
            for(int j = i + 1; j < parsed.size(); j++) {
                double[] res = getIntersect(parsed.get(i), parsed.get(j));
                if(res[2] > 0 && res[3] > 0 && res[0] > minBound && res[0] < maxBound && res[1] > minBound && res[1] < maxBound)
                    sum++;
            }

        return String.valueOf(sum);
    }

    private double[] getIntersect(double[] a, double[] b) {
        double resX = (b[1] - a[1] + a[3]*a[0]/a[2] - b[3]*b[0]/b[2]) / (a[3]/a[2] - b[3]/b[2]);
        double resY = a[1] + a[3]/a[2]*(resX - a[0]);
        return new double[] {
                resX,
                resY,
                (resX - a[0]) / a[2],
                (resX - b[0]) / b[2]
        };
    }

    public String pointTwo(String input) {
        return "Done by hand (and wolfram alpha). Only first three input lines required.";
    }

}
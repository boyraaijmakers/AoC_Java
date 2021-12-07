package org.boy.aoc.twentytwentyone.exercises;


import java.util.Arrays;

public class day7 extends SolutionTemplate {
    public String pointOne(String input) {
        return String.valueOf(this.getMinFuelCost(input, false));
    }

    public String pointTwo(String input) {
        return String.valueOf(this.getMinFuelCost(input, true));
    }

    private int getMinFuelCost(String input, boolean incremental) {
        int[] inputInts = this.inputToIntArray(input, ",");
        int max = Arrays.stream(inputInts).max().getAsInt();
        int minFuel = max * inputInts.length * inputInts.length;

        for (int i = 0; i <= max; i++)
            minFuel = Math.min(minFuel, this.getFuelCost(inputInts, i, incremental));
        
        return minFuel;
    }

    private int getFuelCost(int[] inputs, int target, boolean incremental) {
        int fuelCost = 0;
        for (int input: inputs)
            fuelCost += incremental ?
                    (Math.abs(input - target)) * (1 + Math.abs(input - target)) / 2 :
                    Math.abs(input - target) ;

        return fuelCost;
    }
}



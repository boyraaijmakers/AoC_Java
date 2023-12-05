package org.boy.aoc.twentytwentythree;

import org.boy.aoc.twentytwentythree.exercises.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Main Advent of Code class
 *
 */
public class AocMain
{
    public static void main( String[] args ) throws IOException {
        AocMain main = new AocMain();

        String inputFileLocation = args[0] + args[1] + ".txt";
        int dayNumber = Integer.parseInt(args[1]);
        int pointNumber = Integer.parseInt(args[2]);

        long timeStart = System.currentTimeMillis();
        System.out.println(
                main.runDay(
                        dayNumber,
                        pointNumber,
                        main.readInput(inputFileLocation)
                )
        );
        long timeEnd = System.currentTimeMillis();

        System.out.println("\nSolution time: " + (timeEnd - timeStart) + "ms");
    }

    public String runDay(int dayNumber, int pointNumber, String input) {
        SolutionTemplate[] days = {
                new day1(), new day2(), new day3(), new day4(), new day5()
        };

        return (pointNumber == 1) ? days[dayNumber - 1].pointOne(input) : days[dayNumber - 1].pointTwo(input);
    }

    public String readInput(String fileLocation) throws IOException {
        return Files.readString(Path.of(fileLocation));
    }
}

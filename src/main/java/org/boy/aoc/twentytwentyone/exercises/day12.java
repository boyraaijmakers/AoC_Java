package org.boy.aoc.twentytwentyone.exercises;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class day12 extends SolutionTemplate {

    public String pointOne(String input) {
        return String.valueOf(makeStep("start", init(input), new HashSet<>(), true));
    }

    public String pointTwo(String input) {
        return String.valueOf(makeStep("start", init(input), new HashSet<>(), false));
    }

    private HashMap<String, ArrayList<String>> init(String input) {
        HashMap<String, ArrayList<String>> routes = new HashMap<>();

        for (String line: input.split("\n")) {
            String[] parts = line.split("-");

            if (!routes.containsKey(parts[0].strip())) routes.put(parts[0].strip(), new ArrayList<>());
            if (!routes.containsKey(parts[1].strip())) routes.put(parts[1].strip(), new ArrayList<>());

            routes.get(parts[0].strip()).add(parts[1].strip());
            routes.get(parts[1].strip()).add(parts[0].strip());
        }
        return routes;
    }

    private int makeStep(String currentPlace, HashMap<String, ArrayList<String>> routes,
                            HashSet<String> check, boolean doubleCheck) {

        if (currentPlace.equals("end"))
            return 1;

        if (currentPlace.toLowerCase().equals(currentPlace)) {
            if (check.contains(currentPlace) && !doubleCheck) doubleCheck = true;
            else check.add(currentPlace);
        }

        int routesNum = 0;

        for (String point: routes.get(currentPlace))
            if (!point.equals ("start") && ( !doubleCheck || !check.contains(point) ))
                routesNum += makeStep(point, routes, new HashSet<>(check), doubleCheck);

        return routesNum;
    }
}



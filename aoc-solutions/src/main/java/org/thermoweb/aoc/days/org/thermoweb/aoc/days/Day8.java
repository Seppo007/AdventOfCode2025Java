package org.thermoweb.aoc.days;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@DaySolver(8)
public class Day8 implements Day {

    static int circuit = 1;

    record ThreeDimCoords(int x, int y, int z) {
        double distanceTo(ThreeDimCoords other) {
            return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
        }
    }

    record Junction(ThreeDimCoords coords1, ThreeDimCoords coords2, double distance) {
    }

    static private List<ThreeDimCoords> parseInput(String input) {
        List<ThreeDimCoords> parsedInput = new ArrayList<>();

        for (String str : input.split("\n")) {
            Integer[] cords = Arrays.stream(str.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
            parsedInput.add(new ThreeDimCoords(cords[0], cords[1], cords[2]));
        }

        return parsedInput;
    }

    static private List<Junction> nearestCordsOrdered(List<ThreeDimCoords> parsedInput) {
        List<Junction> closestJunctions = new ArrayList<>();

        for (ThreeDimCoords coords : parsedInput) {
            double minDistance = Double.MAX_VALUE;
            ThreeDimCoords nearestCords = null;
            for (ThreeDimCoords candidate : parsedInput) {
                if (coords.equals(candidate)) continue;
                double distance = coords.distanceTo(candidate);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCords = candidate;
                }
            }
            if (nearestCords == null) continue;
            closestJunctions.add(new Junction(coords, nearestCords, minDistance));
        }

        return closestJunctions.stream().sorted((junction1, junction2) -> {
            if (junction1.distance > junction2.distance) return 1;
            if (junction1.distance < junction2.distance) return -1;
            return 0;
        }).toList();
    }

    @Override
    public Optional<BigInteger> partOne(String input) {
        List<ThreeDimCoords> parsedInput = parseInput(input);

        List<Junction> closestJunctions = nearestCordsOrdered(parsedInput);

        HashMap<ThreeDimCoords, Integer> circuits = new HashMap<>();

        for (Junction junction : closestJunctions) {
            if (circuits.containsKey(junction.coords1)) {
                circuits.put(junction.coords2, circuits.get(junction.coords1));
            } else if (!circuits.containsKey(junction.coords2)) {
                circuits.put(junction.coords2, circuit++);
            } else {
                circuits.put(junction.coords1, circuits.get(junction.coords2));
            }
        }

        List<Entry<ThreeDimCoords, Integer>> list = circuits.entrySet().stream().sorted((a, b) -> {
                    if (a.getValue() > b.getValue()) return 1;
                    if (a.getValue() < b.getValue()) return -1;
                    return 0;
                }
        ).toList();

        List<Integer> threeBiggestCircuitSizes = list.stream().collect(Collectors.groupingBy(coords -> coords.getValue()))
                .entrySet()
                .stream()
                .map(entry -> entry
                        .getValue().size())
                .sorted(Comparator.reverseOrder())
                .toList()
                .subList(0, 3);

        return Optional.empty();
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}

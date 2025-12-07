package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(6)
public class Day6 implements Day {

    private List<List<String>> parseInput(String input) {

        /*
        123 328 51  64
        45  64  387 23
        6   98  215 314
        *   +   *   +
         */

        String[] lines = input.split("\n");
        String[][] entriesByLines = new String[lines.length][];
        List<List<String>> mathEntries = new ArrayList<>();

        for(int i = 0; i < lines.length; i++) {
            entriesByLines[i] = Arrays.stream(lines[i].trim().split("\\s+")).map(entry -> entry).toArray(String[]::new);
        }

        int entriesLength = entriesByLines[0].length;
        for(int i = 0; i < entriesLength; i++){
            List<String> stringList = new ArrayList<>();
            for(int j = 0; j < lines.length; j++) {
                stringList.add(entriesByLines[j][i]);
            }
            mathEntries.add(stringList);
        }

        return mathEntries;
    }

    @Override
    public Optional<BigInteger> partOne(String input) {

        List<List<String>> parsedInput = parseInput(input);

        long result = 0;

        for (List<String> problem : parsedInput) {
            if(problem.contains("+")) {
                long sum = 0L;
                for(int i = 0; i < problem.size() - 1; i++) {
                    sum += Long.parseLong(problem.get(i));
                }
                result += sum;
            } else {
                long product = 1L;
                for(int i = 0; i < problem.size() - 1; i++) {
                    product *= Long.parseLong(problem.get(i));
                }
                result += product;
            }
        }

        return Optional.of(BigInteger.valueOf(result));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}

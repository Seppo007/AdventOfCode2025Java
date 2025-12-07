package org.thermoweb.aoc.days;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DaySolver(2)
public class Day2 implements Day {

    private static List<long[]> parseInput(String input) {
        List<long[]> ranges = new ArrayList<>();
        String[] hyphenSeparatedRanges = input.split(",");

        for (String range : hyphenSeparatedRanges) {
            String[] currentRange = range.split("-");
            ranges.add(new long[]{
                    Long.parseLong(currentRange[0]),
                    Long.parseLong(currentRange[1])
            });
        }

        return ranges;
    }

    @Override
    public Optional<BigInteger> partOne(String input) {

        List<long[]> ranges = parseInput(input);

        long result = 0;

        for (long[] range : ranges) {
            for (long i = range[0]; i <= range[1]; i++) {
                String str = String.valueOf(i);
                if (str.length() % 2 == 0 &&
                        str.startsWith(str.substring(str.length() / 2))) {
                    result += i;
                }
            }
        }

        return Optional.of(BigInteger.valueOf(result));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}

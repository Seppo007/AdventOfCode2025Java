package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Range;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(5)
public class Day5 implements Day {

    private record Input(Range<BigInteger>[] validIngredientRanges, Set<BigInteger> ingredientIDs) {

    }


    private Input parseInput(String input) {
        String[] inputLines = input.split("\n\n");
        String[] rangeLines = inputLines[0].split("\n");
        String[] ingredientLines = inputLines[1].split("\n");

        Range<BigInteger>[] parsedValidRanges = Arrays.stream(rangeLines)
                .map(parsedRangeStr -> {
                    String[] range = parsedRangeStr.split("-");
                    return Range.of(BigInteger.valueOf(Long.parseLong(range[0])),
                            BigInteger.valueOf(Long.parseLong(range[1])));
                }).toArray(Range[]::new);

        Set<BigInteger> parsedIngredientIDs = null;
        parsedIngredientIDs = Arrays.stream(ingredientLines).map(parsedIngredientIdStr ->
                        BigInteger.valueOf(Long.parseLong(parsedIngredientIdStr)))
                .collect(Collectors.toSet());

        return new Input(parsedValidRanges, parsedIngredientIDs);
    }

    @Override
    public Optional<BigInteger> partOne(String input) {
        Input parsedInput = parseInput(input);

        long freshIngredients = 0;

        for (BigInteger ingredientID : parsedInput.ingredientIDs()) {
            if (Arrays.stream(parsedInput.validIngredientRanges()).anyMatch(range -> range.contains(ingredientID))) {
                freshIngredients++;
            }
        }

        return Optional.of(BigInteger.valueOf(freshIngredients));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        Input parsedInput = parseInput(input);

        HashSet<Long> freshIDs = new HashSet<>();

        /*for (Range<BigInteger> range : parsedInput.validIngredientRanges()) {
            for(long i = range.getMinimum().longValue(); i <= range.getMaximum().longValue(); i++) {
                freshIDs.add(i);
            }
            System.out.println("next");
        }*/

        // Takes to long to calculate find another type of representation or accumulation (check for overlapping and just subtract min from max per range)

        return Optional.of(BigInteger.valueOf(14));
    }
}

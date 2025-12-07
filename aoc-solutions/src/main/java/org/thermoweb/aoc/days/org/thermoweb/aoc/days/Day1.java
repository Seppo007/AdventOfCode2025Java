package org.thermoweb.aoc.days;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@DaySolver(1)
public class Day1 implements Day {

    final static int DIAL_START = 50;
    final static int MAX_DIAL = 100;


    private static List<Integer> parseInputAsRotations(String input) {
        return Arrays.stream(input.split("\n")).map(entry -> {
                    int value = Integer.parseInt(entry.substring(1));
                    return entry.startsWith("L") ? -value : value;
                }
        ).toList();
    }

    @Override
    public Optional<BigInteger> partOne(String input) {

        List<Integer> rotations = parseInputAsRotations(input);

        int dial = DIAL_START;
        int result = 0;

        for (int rotation : rotations) {
            dial = (dial + rotation) % MAX_DIAL;
            if (dial == 0) result++;
        }

        return Optional.of(BigInteger.valueOf(result));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        List<Integer> rotations = parseInputAsRotations(input);

        int dial = DIAL_START;
        int result = 0;

        BiFunction<Integer, Integer, Integer> clickCount = (currentDial, rotation) -> {
            int value = Math.abs(currentDial + rotation);

            if (value == 0) {
                return 1;
            }
            else if (value >= MAX_DIAL) {
                return value / MAX_DIAL;
            }
            else {
                return 0;
            }
        };

        for (int rotation : rotations) {
           result += clickCount.apply(dial, rotation);

           dial = (dial + rotation) % MAX_DIAL;
           if(dial < 0 ) dial = dial + MAX_DIAL;
        }

        return Optional.of(BigInteger.valueOf(result));
    }
}

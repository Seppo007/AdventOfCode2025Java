package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(3)
public class Day3 implements Day {

    private record Entry(int value, int index) {
    }

    private List<List<Integer>> parseInput(String input) {
        String[] lines = input.split("\n");
        List<List<Integer>> batteryBanks = Collections.emptyList();

        batteryBanks = Arrays
                .stream(lines).map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
                .toList();

        return batteryBanks;
    }

    private int getMaxJoltageFrom(List<Integer> energyBank) {
        List<Integer> energyBankWithoutLastIndex = energyBank.subList(0, energyBank.size() - 1);
        int firstMax = Collections.max(energyBankWithoutLastIndex);
        int indexOfFirstMax = energyBankWithoutLastIndex.indexOf(firstMax);

        int secondMax = Collections.max(energyBank.subList(indexOfFirstMax + 1, energyBank.size()));

        return (firstMax * 10) + secondMax;
    }

    @Override

    public Optional<BigInteger> partOne(String input) {
        List<List<Integer>> banks = parseInput(input);

        int sum = 0;

        for (List<Integer> bank : banks) {
            sum += getMaxJoltageFrom(bank);
        }

        return Optional.of(BigInteger.valueOf(sum));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}

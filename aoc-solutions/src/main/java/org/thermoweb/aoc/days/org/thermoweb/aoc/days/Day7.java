package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.Optional;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(7)
public class Day7 implements Day {

    static private String[][] parseInput(String input) {

        String[] lines = input.split("\n");
        int lineLength = lines[0].length();
        int columnCount = lines.length;

        String[][] parsedInput = new String[columnCount][lineLength];

        for (int i = 0; i < columnCount; i++) {
            for(int j = 0; j < lineLength; j++) {
                parsedInput[i][j] = String.valueOf(lines[i].charAt(j));
            }
        }

        return parsedInput;
    }

    static private String[][] insertTachyonBeamsIn(String input) {
        String[][] parsedInput = parseInput(input);
        int lineLength = parsedInput[0].length;
        int columnCount = parsedInput.length;


        for(int i = 1; i < columnCount; i++) {
            for(int j = 0; j < lineLength; j++) {
                if(parsedInput[i - 1][j].equals("S")) {
                    parsedInput[i][j] = "|";
                }
                if(parsedInput[i][j].equals("^")) {
                    parsedInput[i][j-1] = "|";
                    parsedInput[i][j+1] = "|";
                } else if (parsedInput[i-1][j].equals("|")) {
                    parsedInput[i][j] = "|";
                }
            }
        }

        return parsedInput;
    }

    static private Long printAndCountTachyonMatrix(String[][] matrix) {
        long count = 0;
        int lineLength = matrix[0].length;
        int columnCount = matrix.length;

        for(int i = 0; i < columnCount; i++) {
            for(int j = 0; j < lineLength; j++) {
                if(matrix[i][j].equals("^") && matrix[i-1][j].equals("|")) count++;
            }
        }

        for(String[] line : matrix) {
            System.out.println(String.join("", line));
        }

        return count;
    }

    @Override
    public Optional<BigInteger> partOne(String input) {

        String[][] parsedInputWithBeams = insertTachyonBeamsIn(input);
        long count = printAndCountTachyonMatrix(parsedInputWithBeams);

        return Optional.of(BigInteger.valueOf(count));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}

package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(4)
public class Day4 implements Day {

    final static int MAX_ADJACENT_PAPERS = 4;

    private static String[][] parseInput(String input) {
        String[] lines = input.split("\n");

        int horizontalBoxes = lines[0].length();
        int verticalBoxes = lines.length;

        String[][] diagram = new String[verticalBoxes][horizontalBoxes];

        for (int i = 0; i < verticalBoxes; i++) {
            diagram[i] = lines[i].split("");
        }

        return diagram;
    }

    private static boolean hasLessThanFourAdjacentPapers(String[][] diagram, int x, int y) {
        /* Example:
              x 0 1 2 3 4 5 6 7 8 9
            y 0 . . @ @ . @ @ @ @ .
            y 1 @ @ @ . @ . @ . @ @
            y 2 @ @ @ @ @ . @ . @ @
         */

        int sumOfNeighbours = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int checkY = y + i;
                int checkX = x + j;

                if (checkY >= 0 && checkY < diagram.length && checkX >= 0 && checkX < diagram[0].length) {
                    if (diagram[checkY][checkX].equals("@")) {
                        sumOfNeighbours++;
                    }
                }
            }
        }

        return sumOfNeighbours < MAX_ADJACENT_PAPERS;
    }

    @Override
    public Optional<BigInteger> partOne(String input) {
        String[][] diagram = parseInput(input);

        int papersAvailableForMovement = 0;

        for (int y = 0; y < diagram.length; y++) {
            for (int x = 0; x < diagram[0].length; x++) {
                if (diagram[y][x].equals("@") && hasLessThanFourAdjacentPapers(diagram, x, y))
                    papersAvailableForMovement++;
            }
        }

        return Optional.of(BigInteger.valueOf(papersAvailableForMovement));
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}

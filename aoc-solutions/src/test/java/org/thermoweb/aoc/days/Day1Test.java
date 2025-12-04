package org.thermoweb.aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Exception;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DayRunner;

class Day1Test {
    private final Day day = new Day1();

    @Test
    void test_part_one() throws Exception {
        assertEquals(Optional.of(BigInteger.valueOf(3)), day.partOne(DayRunner.getExample(1)));
    }

    @Test
    void test_part_two() throws Exception {
        assertEquals(BigInteger.valueOf(6), day.partTwo(DayRunner.getExample(1)));
    }
}

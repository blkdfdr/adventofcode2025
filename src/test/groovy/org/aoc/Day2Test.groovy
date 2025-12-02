package org.aoc

import com.navigamez.greex.GreexGenerator
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals


class Day2Test {
    @Test
    void testRangeCollector(){
        Day2 day2 = new Day2()
        Day2.Part1 part1 = new Day2.Part1(day2)
        GreexGenerator generator = new GreexGenerator(~/([1-9][0-9]*)\\g{1}/ as String);
        100000.times {
            def cur = generator.generateRandom() as Integer
            assertEquals(cur, part1.rangeCollector(cur), "${cur} Wasn't recognized as invalid")
        }
    }
}

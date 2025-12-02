package org.aoc

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals


class Day2Test {
    @Test
    void testPart1RangeCollector(){
        Day2 day2 = new Day2()
        Day2.Part1 part1 = new Day2.Part1(day2)
        Random random = new Random();
        random.ints(1, 2^16-1).limit(100000).each {
            def cur = "$it$it" as Integer
            assertEquals(cur, part1.rangeCollector(cur), "${cur} Wasn't recognized as invalid")
        }
    }

    @Test
    void testPart2RangeCollector(){
        Day2 day2 = new Day2()
        Day2.Part2 part2 = new Day2.Part2(day2)
        Random random = new Random();
        random.ints(1, 2^16-1).limit(100000).each {it->
            def cur = "$it".repeat(random.nextInt(2, Math.floor(19/Math.ceil(Math.log10(it+1))) as Integer)) as Long
            if(cur != part2.rangeCollector(cur)){
                part2.rangeCollector(cur)
            }
            assertEquals(cur, part2.rangeCollector(cur), "${cur} Wasn't recognized as invalid")
        }
    }
}

package org.aoc

import org.junit.jupiter.api.Test

import java.util.stream.Stream

import static org.junit.jupiter.api.Assertions.assertEquals

class Day3Test {
    @Test
    void testPart1RangeCollector(){
        Day3 day3 = new Day3()
        Random random = new Random()
        100000.times {
            Integer highestLeft = random.nextInt(1, 10)
            Integer highestRight = random.nextInt(1, highestLeft+1)
            Integer indexLeft = random.nextInt(0, 99)
            Integer indexRight = random.nextInt(indexLeft + 1, 100)
            Integer lowest = Math.min(highestLeft, highestRight) + 1
            List<Integer> full = random.ints(1, lowest).limit(100).collect()
            full[indexLeft] = highestLeft
            full[indexRight] = highestRight

            def fullNumber = "$highestLeft$highestRight" as Integer

            Day3.Part part = new Day3.Part1(day3)
            part.operate(full)
            assertEquals(fullNumber, part.counter)
        }
    }

    @Test
    void testPart2RangeCollector(){
        Day3 day3 = new Day3()
        Random random = new Random(1000)
        100000.times {
            def lastIndex = -1
            def count = 1
            def highest = random.ints(1,10).limit(Day3.SELECTABLE).collect{cur->
                [:].tap{
                    lastIndex = index = random.nextInt(lastIndex + 1, Day3.MAX_LENGTH - Day3.SELECTABLE + count)
                    value = cur
                    count++
                }
            }
            Integer lowest = highest.collect{it.value}.min() + 1
            List<Integer> full = random.ints(1, lowest).limit(Day3.MAX_LENGTH).collect()

            highest.each {
                full[it.index]=it.value
            }

            def fullNumber = highest.collect{it.value}.join() as Long

            Day3.Part part = new Day3.Part2(day3)
            println fullNumber
            part.operate(full)
            assertEquals(fullNumber, part.counter, "List was: ${full.join()}")
        }
    }
}

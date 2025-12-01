package org.aoc

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals

class Day1Test {
    @Test
    void testPart1RightTurn() {
        def Day1 day1 = new Day1()
        def part1 = new Day1.Part1(day1)
        part1.operate('R', 50)
        assertEquals(100, part1.state)
        assertEquals(1, part1.zerocounter)
    }

    @Test
    void testPart1LeftTurn() {
        def Day1 day1 = new Day1()
        def part1 = new Day1.Part1(day1)
        part1.operate('L', 50)
        assertEquals(0, part1.state)
        assertEquals(1, part1.zerocounter)
    }

    @Test
    void testPart2RightTurn() {
        def day1 = new Day1()
        def part2 = new Day1.Part2(day1)
        part2.operate('R', 20)
        assertEquals(0, part2.zerocounter)
        part2.operate('R', 90)
        assertEquals(1, part2.zerocounter)
    }

    @Test
    void testPart2LeftTurn() {
        def day1 = new Day1()
        def part2 = new Day1.Part2(day1)
        part2.operate('L', 20)
        assertEquals(0, part2.zerocounter)
        part2.operate('L', 90)
        assertEquals(1, part2.zerocounter)
    }

    @Test
    void testPart2MultipleRightZeroCrossings() {
        def day1 = new Day1()
        def part2 = new Day1.Part2(day1)
        part2.operate('R', 250)
        assertEquals(3, part2.zerocounter)
        assertEquals(0, part2.state)

        part2.operate('R', 33)
        assertEquals(3, part2.zerocounter)

        part2.operate('R', 97)
        assertEquals(4, part2.zerocounter)
        assertEquals(30, part2.state)
    }

    @Test
    void testPart2MultipleLeftZeroCrossings() {
        def day1 = new Day1()
        def part2 = new Day1.Part2(day1)
        part2.operate('L', 250)
        assertEquals(3, part2.zerocounter)
        assertEquals(0, part2.state)
        part2.operate('L', 133)
        assertEquals(4, part2.zerocounter)
        assertEquals(100 - 33, part2.state)
    }

    @Test
    void testPart2Example() {
        def day1 = new Day1()
        def part2 = new Day1.Part2(day1)

        part2.operate('L', 68)
        assertEquals(1, part2.zerocounter)
        assertEquals(82, part2.state)

        part2.operate('L', 30)
        assertEquals(1, part2.zerocounter)
        assertEquals(52, part2.state)

        part2.operate('R', 48)
        assertEquals(2, part2.zerocounter)
        assertEquals(0, part2.state)

        part2.operate('L', 5)
        assertEquals(2, part2.zerocounter)
        assertEquals(95, part2.state)

        part2.operate('R', 60)
        assertEquals(3, part2.zerocounter)
        assertEquals(55, part2.state)

        part2.operate('L', 55)
        assertEquals(4, part2.zerocounter)
        assertEquals(0, part2.state)

        part2.operate('L', 1)
        assertEquals(4, part2.zerocounter)
        assertEquals(99, part2.state)

        part2.operate('L', 99)
        assertEquals(5, part2.zerocounter)
        assertEquals(0, part2.state)

        part2.operate('R', 14)
        assertEquals(5, part2.zerocounter)
        assertEquals(14, part2.state)

        part2.operate('L', 82)
        assertEquals(6, part2.zerocounter)
        assertEquals(32, part2.state)
    }

    @Test
    void testPart2ConsecutiveZeroCrossingsBothDirections() {
        def day1 = new Day1()
        def part2 = new Day1.Part2(day1)

        // Right direction: hit zero, then hit zero again with a 100-step
        part2.operate('R', 50)
        assertEquals(1, part2.zerocounter)
        assertEquals(0, part2.state)

        part2.operate('R', 100)
        assertEquals(2, part2.zerocounter)
        assertEquals(0, part2.state)

        // New Part2 instance for left direction
        part2 = new Day1.Part2(day1)

        // Left direction: hit zero, then hit zero again with a 100-step
        part2.operate('L', 50)
        assertEquals(1, part2.zerocounter)
        assertEquals(0, part2.state)

        part2.operate('L', 100)
        assertEquals(2, part2.zerocounter)
        assertEquals(0, part2.state)
    }

    @Test
    void testPart2FuzzCompareOperateVsOperateAlt() {
        def day1 = new Day1()
        int trials = 200
        int maxOps = 100
        long seed = System.currentTimeMillis()
        def rng = new Random(seed)

        trials.times { t ->
            def ops = []
            int opsCount = rng.nextInt(maxOps) + 1
            def pStep = new Day1.Part2(day1)
            def pAlt = new Day1.Part2(day1)

            int initState = rng.nextInt(100)
            int initZeros = rng.nextInt(10)
            pStep.state = initState
            pStep.zerocounter = initZeros
            pAlt.state = initState
            pAlt.zerocounter = initZeros

            opsCount.times { i ->
                def dir = rng.nextBoolean() ? 'L' : 'R'
                int amount = rng.nextInt(1000)
                ops << "${dir}${amount}"

                pStep.operate(dir, amount)
                pAlt.operateAlt(dir, amount)

                assertEquals(pStep.state, pAlt.state, "State mismatch. Seed: ${seed} trial:${t} ops:${ops.join(',')}")
                assertEquals(pStep.zerocounter, pAlt.zerocounter, "Zero counter mismatch. Seed: ${seed} trial:${t} ops:${ops.join(',')}")
            }
        }
    }
}
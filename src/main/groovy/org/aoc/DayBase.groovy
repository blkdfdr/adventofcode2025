package org.aoc

abstract class DayBase {
    File getInput(){
        getClass().with {
            new File(it.getResource("/${it.getSimpleName().toLowerCase()}.txt").toURI())
        }
    }
    abstract void part1()
    abstract void part2()
}
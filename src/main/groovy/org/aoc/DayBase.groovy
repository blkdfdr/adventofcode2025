package org.aoc

abstract class DayBase {
    File getInput(){
        getClass().with {
            new File(it.getResource("/${it.getSimpleName().toLowerCase()}.txt").toURI())
        }
    }
    void part1(){
        def part = getClass().getNestMembers().find{
            it.simpleName == 'Part1'
        }?.newInstance(this)
        if(part){
            part.run()
        } else {
            throw new UnsupportedOperationException("Part 1 not implemented")
        }
    }
    void part2(){
        def part = getClass().getNestMembers().find{
            it.simpleName == 'Part2'
        }?.newInstance(this)
        if(part){
            part.run()
        } else {
            throw new UnsupportedOperationException("Part 2 not implemented")
        }
    }
}
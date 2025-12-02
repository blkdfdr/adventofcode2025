package org.aoc

class Day2 extends DayBase {
    void collector(Closure c) {
        getInput().text.split(/, /).each c
    }
    abstract class Part {
        def counter
        abstract void operate(Integer f, Integer l)
        void run(){
            collector this::operate
            println counter
        }
    }
    class Part1 extends Part{
        Integer rangeCollector(it){
            def length = Math.ceil(Math.log10(it)) as Integer
            if(length % 2 == 0) {
                def halfLength = length / 2
                def leftPart = it / Math.pow(10, halfLength)
                def rightPart = it - leftPart
                if (leftPart == rightPart)
                    return it
            }
            0
        }
        void operate(Integer f, Integer l) {
            counter += (f..l).collect(this::rangeCollector).sum()
        }
    }
    class Part2 extends Part{
        void operate(Integer f, Integer l) {

        }
    }
}
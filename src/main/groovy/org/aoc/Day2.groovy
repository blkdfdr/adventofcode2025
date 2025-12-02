package org.aoc

class Day2 extends DayBase {
    void collector(Closure c) {
        getInput().text.split(/,/).collect{
            it.split('-').with {
                [it[0] as Long, it[1] as Long]
            }
        }.each c
    }
    abstract class Part {
        Long counter = 0
        void operate(Long f, Long l) {
            counter += (f..l).collect(this::rangeCollector).sum()
        }
        abstract Long rangeCollector(Long it)
        void run(){
            collector this::operate
            println counter
        }
    }
    class Part1 extends Part{
        Long rangeCollector(Long it){
            def length = Math.ceil(Math.log10(it)) as Long
            if(length % 2 == 0) {
                Long halfDigits = Math.pow(10, length / 2 )
                Long leftPart = it / halfDigits
                Long rightPart = it - leftPart * halfDigits
                if (leftPart == rightPart)
                    return it
            }
            0
        }
    }
    class Part2 extends Part{
        Long rangeCollector(Long num) {
            Integer length = Math.ceil(Math.log10(num))
            if(length>1) {
                if((1..(length/2 as Integer)).findAll {
                    length % it == 0
                }.find {
                    "$num".collect().collate(it).toSet().size()==1
                }) return num
            }
            0
        }
    }
}
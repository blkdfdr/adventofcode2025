package org.aoc

class Day1 extends DayBase {
    void collector(Closure c){
        getInput().eachLine {
            (it=~/^(?<dir>[LR])(?<amount>\d{1,3})$/).collect{_,dir,amount->
                [dir as Character, amount as Integer]
            }.each c
        }
    }
    void part1() {
        def state = 50
        def zerocounter = 0
        collector{ dir, amount->
            switch (dir){
                case 'L'-> state -= amount
                case 'R' -> state += amount
            }
            if (state % 100 == 0){
                zerocounter++
            }
        }
        println(zerocounter)
    }
    void part2() {
        def state = 50
        def zerocounter = 0
        collector { dir, amount ->
            zerocounter += amount.intdiv(100)
            amount %= 100
            switch (dir) {
                case 'L' -> {
                    if ((state - amount) % 100 > state) {
                        zerocounter++
                    }
                    state -= amount
                }
                case 'R' -> {
                    if ((state + amount) % 100 > state) {
                        zerocounter++
                    }
                    state += amount
                }
            }
        }
        println(zerocounter)
    }
    abstract class Part{
        int state = 50
        int zerocounter = 0
        abstract void operate(Character dir, Integer amount)
        void run(){
            collector(operate as Closure)
        }
    }
    class Part1 extends Part{
        void operate(Character dir, Integer amount) {

        }
    }
}
package org.aoc

class Day1 extends DayBase {
    void collector(Closure c) {
        (getInput().text=~/(?m)^(?<dir>[LR])(?<amount>\d{1,3})$/).collect{_,dir,amount->
            [dir as String, amount as Integer]
        }.each c
    }
    abstract class Part {
        def state = 50
        int zerocounter = 0
        abstract void operate(String dir, Integer amount)
        void run(){
            collector this::operate
            println zerocounter
        }
    }
    class Part1 extends Part{
        void operate(String dir, Integer amount) {
            switch (dir){
                case 'L'-> state -= amount
                case 'R' -> state += amount
            }
            if (state % 100 == 0){
                zerocounter++
            }
        }
    }
    class Part2 extends Part{
        void operateAlt(String dir, Integer amount) {
            int hundreds = amount.intdiv(100)
            int rem = amount % 100
            zerocounter += hundreds
            if (rem != 0) {
                if (dir == 'L') {
                    int distance = state % 100
                    if (distance == 0) distance = 100
                    if (rem >= distance) zerocounter++
                    state = Math.floorMod(state - rem, 100)
                } else {
                    int distance = (100 - state) % 100
                    if (distance == 0) distance = 100
                    if (rem >= distance) zerocounter++
                    state = Math.floorMod(state + rem, 100)
                }
            }
        }
        void operate(String dir, Integer amount) {
            int step = dir == 'L' ? -1 : 1
            amount.times {
                state = Math.floorMod(state + step, 100)
                if (state == 0) zerocounter++
            }
        }
    }
}
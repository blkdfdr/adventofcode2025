package org.aoc

class Day3 extends DayBase {
    public final static def SELECTABLE = 12
    public final static def MAX_LENGTH = 100
    void collector(Closure c){
        getInput().collect {
            it.collect{it as Integer}
        }.each c
    }
    abstract class Part {
        Long counter = 0
        abstract void operate(List<Integer> joltages)
        void run(){
            collector this::operate
            println counter
        }
    }
    class Part1 extends Part{
        void operate(List<Integer> joltages) {
            def maxLeft = joltages.dropRight(1).with{list->
                [:].tap {
                    val = list.max()
                    ind = list.indexOf(val)
                }
            }
            def maxRight = joltages.subList(maxLeft.ind + 1, joltages.size()).with{list->
                [:].tap {
                    val = list.max()
                }
            }
            counter += "$maxLeft.val$maxRight.val" as Integer
        }
    }
    class Part2 extends Part{
        void operate(List<Integer> joltages) {
            def rem = SELECTABLE
            def select
            select = {List<Integer> list ->
                def max = list.max()
                if(!max||rem == 0)return ""
                rem--
                def index = list.indexOf(max)
                def left = list.take(index)
                def right = list.drop(index + 1)
                def rsel = select(right)
                return "${rem>0?select(left):""}$max$rsel"
            }
            counter += select(joltages) as Long
        }
    }
}
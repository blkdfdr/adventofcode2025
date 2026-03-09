package org.aoc

import org.apache.commons.math3.linear.*

class Day8 extends DayBase {
    public final static def TOP = 3
    class Box{
        ArrayRealVector pos
        Box(Integer[] pos){
            this.pos = new ArrayRealVector(pos)
        }

        @Override
        int hashCode() {
            return Objects.hash(pos)
        }
    }
    class BoxGraph {
        HashMap<Box,Box> adjacency
        BoxGraph(List<Box> boxes){
            boxes.collate(10, 1, true).each { list ->
                def cur = list[0]
                adjacency[cur] = list.drop(1).min {
                    it.pos.getDistance(cur.pos)
                }
            }
        }
        def closest(Box){

        }
    }
    abstract class Part {
        Long counter = 0
        abstract void operate(List<Integer> joltages)
        void run(){
            println counter
        }
    }
}
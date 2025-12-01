package org.aoc

import org.codehaus.groovy.reflection.ReflectionUtils

Calendar cal = Calendar.getInstance()
def date = cal.get(Calendar.DATE)

DayBase curDay = ReflectionUtils.forName("org.aoc.Day$date").getConstructor().newInstance() as DayBase
println("Part1:")
curDay.part1()
println("Part2:")
curDay.part2()
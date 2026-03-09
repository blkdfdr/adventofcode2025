package org.aoc

import org.codehaus.groovy.reflection.ReflectionUtils
import groovy.cli.picocli.CliBuilder

Calendar cal = Calendar.getInstance()
def date = cal.get(Calendar.DATE)

def cli = new CliBuilder(usage: 'gradle run --args="<options>"').tap {
    h(longOpt: 'help', 'Show help')
    d(longOpt: 'date', args: 1, type: Integer, 'Date')
}

options = cli.parse(args)
if(options.d){
    date = options.d
}

DayBase curDay = ReflectionUtils.forName("org.aoc.Day$date").getConstructor().newInstance() as DayBase
println("Part1:")
curDay.part1()
println("Part2:")
curDay.part2()
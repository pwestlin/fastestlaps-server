package nu.westlin.fastestlaps.test

import nu.westlin.fastestlaps.domain.Driver
import nu.westlin.fastestlaps.domain.Track

val peter = Driver(1, "Peter")
val adam = Driver(2, "Adam")
val allDrivers = listOf(peter, adam)

val hedemora = Track(1, "Hedemora")
val amsberg = Track(2, "Hedemora")
val allTracks = listOf(hedemora, amsberg)
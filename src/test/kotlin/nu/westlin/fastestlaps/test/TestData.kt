package nu.westlin.fastestlaps.test

import nu.westlin.fastestlaps.domain.Driver
import nu.westlin.fastestlaps.domain.Kart.JUNIOR125
import nu.westlin.fastestlaps.domain.Kart.KZ2
import nu.westlin.fastestlaps.domain.Laptime
import nu.westlin.fastestlaps.domain.Track
import java.time.LocalDate

// TODO petves: Move this class to src (or extend it from src)?
// TODO petves: Fix inits with atomic long/int and "stream": http://www.youtube.com/watch?v=h6nMjjxJWjk&t=8m32s

val peter = Driver(1, "Peter")
val adam = Driver(2, "Adam")
val felix = Driver(2, "Felix")
val allDrivers = mutableListOf(peter, adam, felix)

val hedemora = Track(1, "Hedemora")
val amsberg = Track(2, "Amsberg")
val vasteras = Track(3, "Västerås")
val allTracks = mutableListOf(hedemora, amsberg, vasteras)


val laptimePeterHedemora1 = Laptime(1, LocalDate.now().minusDays(32), peter, KZ2, hedemora, 31.26)
val laptimePeterHedemora2 = Laptime(2, LocalDate.now().minusDays(3), peter, KZ2, hedemora, 30.81)
val laptimeAdamHedemora3 = Laptime(3, LocalDate.now().minusDays(13), adam, JUNIOR125, hedemora, 31.29)
val laptimeAdamAmsberg4 = Laptime(4, LocalDate.now().minusDays(1), adam, JUNIOR125, amsberg, 39.38)
val allLaptimes = mutableListOf(laptimePeterHedemora1, laptimePeterHedemora2, laptimeAdamHedemora3, laptimeAdamAmsberg4)
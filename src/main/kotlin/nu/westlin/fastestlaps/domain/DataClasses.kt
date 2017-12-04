package nu.westlin.fastestlaps.domain

import java.time.LocalDate

enum class Kart { DD2, KZ2, JUNIOR125 }

data class Driver(val id: Int, val name: String)

data class Track(val id: Int, val name: String)

data class Laptime(val date: LocalDate, val driver: Driver, val kart: Kart, val track: Track, val description: String)
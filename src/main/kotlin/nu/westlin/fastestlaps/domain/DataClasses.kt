package nu.westlin.fastestlaps.domain

import java.time.LocalDate

enum class Kart { DD2, KZ2, JUNIOR125 }

// TODO petves: Genereate id automatically
abstract class DomainEntity(open val id: Int)

data class Driver(override val id: Int, val name: String) : DomainEntity(id)

data class Track(override val id: Int, val name: String) : DomainEntity(id)

data class Laptime(val date: LocalDate, val driver: Driver, val kart: Kart, val track: Track, val description: String)
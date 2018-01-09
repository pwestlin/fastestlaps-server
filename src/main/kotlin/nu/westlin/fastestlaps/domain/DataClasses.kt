package nu.westlin.fastestlaps.domain

import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDate

@Suppress("unused")
enum class Kart(@JsonValue val type: String) { DD2("DD2"), KZ2("KZ2"), JUNIOR125("Junior 125") }

// TODO petves: Genereate id automatically
abstract class Entity(open val id: Int)

data class Driver(override val id: Int, val name: String) : Entity(id)

data class Track(override val id: Int, val name: String) : Entity(id)

data class Laptime(
    override val id: Int,
    val date: LocalDate,
    val driver: Driver,
    val kart: Kart,
    val track: Track,
    val time: Double,
    val description: String? = null
) : Entity(id)
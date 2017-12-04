package nu.westlin.fastestlaps.domain

import org.springframework.stereotype.Repository


interface DriverRepository {
    fun all(): List<Driver>
    fun get(id: Int): Driver?
}

@Repository
class InMemoryDriverRepository(private val drivers: List<Driver>) : DriverRepository {

    constructor() : this(listOf())

    override fun all(): List<Driver> {
        return drivers
    }

    override fun get(id: Int): Driver? {
        return drivers.find { it.id == id }
    }

}
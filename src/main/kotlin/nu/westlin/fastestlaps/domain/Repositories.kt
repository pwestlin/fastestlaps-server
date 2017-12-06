package nu.westlin.fastestlaps.domain

import org.springframework.stereotype.Repository


interface DomainRepository<T> {
    fun all(): List<T>
    fun get(id: Int): T?
    fun create(entity: T)
}

abstract class InMemoryRepository<T>(protected val entities: MutableList<T>) : DomainRepository<T> where T : Entity {

    constructor() : this(mutableListOf())

    override fun all(): List<T> {
        return entities.toList()
    }

    override fun get(id: Int): T? {
        return entities.find { it.id == id }
    }

    override fun create(entity: T) {
        if (entities.find { it.id == entity.id } != null) {
            throw IllegalArgumentException("An entity with id ${entity.id} is already stored")
        } else {
            entities.add(entity)
        }
    }
}

interface DriverRepository : DomainRepository<Driver>

@Repository
class InMemoryDriverRepository(drivers: MutableList<Driver>) : InMemoryRepository<Driver>(drivers), DriverRepository

interface TrackRepository : DomainRepository<Track>

@Repository
class InMemoryTrackRepository(tracks: MutableList<Track>) : InMemoryRepository<Track>(tracks), TrackRepository

interface LaptimeRepository : DomainRepository<Laptime> {
    fun get(predicate: (Laptime) -> Boolean): List<Laptime>
}

@Repository
class InMemoryLaptimeRepository(laptimes: MutableList<Laptime>) : InMemoryRepository<Laptime>(laptimes), LaptimeRepository {
    override fun get(predicate: (Laptime) -> Boolean): List<Laptime> = entities.filter { predicate(it) }
}

package nu.westlin.fastestlaps.domain

import org.springframework.stereotype.Repository


interface DomainRepository<out T> {
    fun all(): List<T>
    fun get(id: Int): T?
}

abstract class InMemoryRepository<T>(protected val objects: List<T>) : DomainRepository<T> where T : DomainEntity {

    constructor() : this(listOf())

    override fun all(): List<T> {
        return objects
    }

    override fun get(id: Int): T? {
        return objects.find { it.id == id }
    }
}

interface DriverRepository : DomainRepository<Driver>

@Repository
class InMemoryDriverRepository(drivers: List<Driver>) : InMemoryRepository<Driver>(drivers), DriverRepository

interface TrackRepository : DomainRepository<Track>

@Repository
class InMemoryTrackRepository(tracks: List<Track>) : InMemoryRepository<Track>(tracks), TrackRepository

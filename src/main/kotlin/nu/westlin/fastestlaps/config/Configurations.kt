package nu.westlin.fastestlaps.config

import nu.westlin.fastestlaps.domain.Driver
import nu.westlin.fastestlaps.domain.InMemoryDriverRepository
import nu.westlin.fastestlaps.domain.InMemoryTrackRepository
import nu.westlin.fastestlaps.domain.Track
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebConfiguration {

    @Bean
    fun inMemoryDriverRepository(): InMemoryDriverRepository {
        return InMemoryDriverRepository(
            listOf(Driver(1, "Peter"), Driver(2, "Adam")))
    }

    @Bean
    fun inMemoryTrackRepository(): InMemoryTrackRepository {
        return InMemoryTrackRepository(
            listOf(Track(1, "Hedemora"), Track(2, "Amsberg")))
    }

}
package nu.westlin.fastestlaps.config

import nu.westlin.fastestlaps.domain.Driver
import nu.westlin.fastestlaps.domain.InMemoryDriverRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebConfiguration {

    @Bean
    fun inMemoryDriverRepository(): InMemoryDriverRepository {
        return InMemoryDriverRepository(
            listOf(Driver(1, "Peter"), Driver(2, "Adam")))
    }
}
package nu.westlin.fastestlaps.config

import nu.westlin.fastestlaps.domain.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class RepositoriesConfiguration {

    @Bean
    fun inMemoryDriverRepository(): InMemoryDriverRepository {
        return InMemoryDriverRepository(
            mutableListOf(Driver(1, "Peter"), Driver(2, "Adam")))
    }

    @Bean
    fun inMemoryTrackRepository(): InMemoryTrackRepository {
        return InMemoryTrackRepository(
            mutableListOf(Track(1, "Hedemora"), Track(2, "Amsberg")))
    }

    @Bean
    fun inMemoryLaptimeRepository(driverRepository: DriverRepository, trackRepository: TrackRepository): InMemoryLaptimeRepository {
        val peter = driverRepository.get(1)!!
        val adam = driverRepository.get(2)!!
        val hedemora = trackRepository.get(1)!!
        val amsberg = trackRepository.get(2)!!

        val laptimePeterHedemora1 = Laptime(1, LocalDate.now().minusDays(32), peter, Kart.KZ2, hedemora, 31.26)
        val laptimePeterHedemora2 = Laptime(2, LocalDate.now().minusDays(3), peter, Kart.KZ2, hedemora, 30.81)
        val laptimeAdamHedemora3 = Laptime(3, LocalDate.now().minusDays(13), adam, Kart.JUNIOR125, hedemora, 31.29)
        val laptimeAdamAmsberg4 = Laptime(4, LocalDate.now().minusDays(1), adam, Kart.JUNIOR125, amsberg, 39.38)

        return InMemoryLaptimeRepository(mutableListOf(laptimePeterHedemora1, laptimePeterHedemora2, laptimeAdamHedemora3, laptimeAdamAmsberg4))
    }

}
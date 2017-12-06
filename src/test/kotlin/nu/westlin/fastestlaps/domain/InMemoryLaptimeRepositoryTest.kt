package nu.westlin.fastestlaps.domain

import nu.westlin.fastestlaps.test.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class InMemoryLaptimeRepositoryTest : UnitTest() {

    val repository = InMemoryLaptimeRepository(allLaptimes)

    @Test
    fun all() {
        assertThat(repository.all())
            .containsExactlyInAnyOrder(laptimePeterHedemora1, laptimePeterHedemora2, laptimeAdamHedemora3, laptimeAdamAmsberg4)
    }

    @Test
    fun getById() {
        assertThat(repository.get(laptimeAdamHedemora3.id))
            .isEqualTo(laptimeAdamHedemora3)
    }

    @Test
    fun createShouldThrowIllegalArgumentExceptionWhenIdAlreadyStored() {
        val laptime = repository.all().first()
        Assertions.assertThatIllegalArgumentException().isThrownBy({ repository.create(laptime) })
            .withMessage("An entity with id ${laptime.id} is already stored")
            .withNoCause()
    }

    @Test
    fun getByPredicateDriver() {
        assertThat(repository.get({ it.driver == peter }))
            .containsExactlyInAnyOrder(laptimePeterHedemora1, laptimePeterHedemora2)
        assertThat(repository.get({ it.driver == adam }))
            .containsExactlyInAnyOrder(laptimeAdamHedemora3, laptimeAdamAmsberg4)
    }

    @Test
    fun getByPredicateDriverAndTrack() {
        assertThat(repository.get({ it.driver == adam && it.track == amsberg }))
            .containsExactly(laptimeAdamAmsberg4)
    }

}
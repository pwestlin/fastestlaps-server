package nu.westlin.fastestlaps.domain

import nu.westlin.fastestlaps.test.UnitTest
import nu.westlin.fastestlaps.test.adam
import nu.westlin.fastestlaps.test.allDrivers
import nu.westlin.fastestlaps.test.peter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class InMemoryDriverRepositoryTest : UnitTest() {

    val repository = InMemoryDriverRepository(allDrivers)

    @Test
    fun all() {
        assertThat(repository.all())
            .containsExactlyInAnyOrder(peter, adam)
    }

    @Test
    fun get() {
        assertThat(repository.get(adam.id))
            .isEqualTo(adam)
    }
}
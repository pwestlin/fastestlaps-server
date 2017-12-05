package nu.westlin.fastestlaps.domain

import nu.westlin.fastestlaps.test.UnitTest
import nu.westlin.fastestlaps.test.allTracks
import nu.westlin.fastestlaps.test.amsberg
import nu.westlin.fastestlaps.test.hedemora
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class InMemoryTrackRepositoryTest : UnitTest() {

    val repository = InMemoryTrackRepository(allTracks)

    @Test
    fun all() {
        assertThat(repository.all())
            .containsExactlyInAnyOrder(hedemora, amsberg)
    }

    @Test
    fun get() {
        assertThat(repository.get(hedemora.id))
            .isEqualTo(hedemora)
    }
}
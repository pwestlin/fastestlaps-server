package nu.westlin.fastestlaps.domain

import nu.westlin.fastestlaps.test.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.Test

class InMemoryTrackRepositoryTest : UnitTest() {

    val repository = InMemoryTrackRepository(allTracks)

    @Test
    fun all() {
        assertThat(repository.all())
            .containsExactlyInAnyOrder(hedemora, amsberg, vasteras)
    }

    @Test
    fun get() {
        assertThat(repository.get(hedemora.id))
            .isEqualTo(hedemora)
    }

    @Test
    fun createShouldThrowIllegalArgumentExceptionWhenIdAlreadyStored() {
        val track = repository.all().first()
        assertThatIllegalArgumentException().isThrownBy({ repository.create(track) })
            .withMessage("An entity with id ${track.id} is already stored")
            .withNoCause()
    }
}
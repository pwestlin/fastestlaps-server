package nu.westlin.fastestlaps.domain

import nu.westlin.fastestlaps.test.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class KartTest : UnitTest() {

    @Test
    fun type() {
        assertThat(Kart.JUNIOR125.type).isEqualTo("Junior 125")
    }

    @Test
    fun json() {
        // TODO petves: Don't know why I need "". From the controller everything works just fine...
        assertThat(objectMapper.writeValueAsString(Kart.JUNIOR125)).isEqualTo("\"Junior 125\"")
    }
}
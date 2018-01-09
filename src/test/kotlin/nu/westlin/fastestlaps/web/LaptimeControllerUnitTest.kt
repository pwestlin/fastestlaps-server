package nu.westlin.fastestlaps.web

import nu.westlin.fastestlaps.domain.LaptimeRepository
import nu.westlin.fastestlaps.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class LaptimeControllerUnitTest : UnitTest() {

    @Mock
    lateinit var laptimeRepository: LaptimeRepository

    lateinit var controller: LaptimeController

    @Before
    fun init() {
        controller = LaptimeController(laptimeRepository)
    }

    @Test
    fun getFastestLaptimesPerClass() {
        assertThat(this.controller.getFastestLaptimesPerClass(allLaptimes))
            .containsExactlyInAnyOrder(
                laptimePeterHedemora2,
                laptimeAdamHedemora3,
                laptimeAdamAmsberg4
            )
    }

}
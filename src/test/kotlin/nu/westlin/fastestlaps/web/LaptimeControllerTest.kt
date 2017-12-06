package nu.westlin.fastestlaps.web

import com.fasterxml.jackson.module.kotlin.readValue
import nu.westlin.fastestlaps.domain.DriverRepository
import nu.westlin.fastestlaps.domain.Laptime
import nu.westlin.fastestlaps.domain.LaptimeRepository
import nu.westlin.fastestlaps.domain.TrackRepository
import nu.westlin.fastestlaps.test.WebIntegrationTest
import nu.westlin.fastestlaps.test.allLaptimes
import nu.westlin.fastestlaps.test.laptimeAdamAmsberg4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(LaptimeController::class)
class LaptimeControllerTest : WebIntegrationTest() {

    @MockBean
    lateinit var laptimeRepository: LaptimeRepository

    @MockBean
    lateinit var trackRepository: TrackRepository

    @MockBean
    lateinit var driverRepository: DriverRepository


    @Test
    fun byParametersAll() {
        `when`(laptimeRepository.all()).thenReturn(allLaptimes)

        val body = mockMvc.perform(MockMvcRequestBuilders.get("/laptimes"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val laptimes: List<Laptime> = this.objectMapper.readValue(body.contentAsString)

        assertThat(laptimes).isEqualTo(allLaptimes)
    }

    @Test
    fun byId() {
        val laptime = laptimeAdamAmsberg4
        `when`(laptimeRepository.get(laptime.id)).thenReturn(laptime)

        val body =
            mockMvc.perform(MockMvcRequestBuilders.get("/laptimes/${laptime.id}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andReturn().response
        val resultLaptime = objectMapper.readValue(body.contentAsString, Laptime::class.java)
        assertThat(resultLaptime).isEqualTo(laptime)
    }
}
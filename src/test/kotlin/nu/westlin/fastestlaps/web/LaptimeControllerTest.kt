package nu.westlin.fastestlaps.web

import com.fasterxml.jackson.module.kotlin.readValue
import nu.westlin.fastestlaps.domain.DriverRepository
import nu.westlin.fastestlaps.domain.Kart.KZ2
import nu.westlin.fastestlaps.domain.Laptime
import nu.westlin.fastestlaps.domain.LaptimeRepository
import nu.westlin.fastestlaps.domain.TrackRepository
import nu.westlin.fastestlaps.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
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

    @Before
    fun init() {
        `when`(laptimeRepository.all()).thenReturn(allLaptimes)
    }

    @Test
    fun byParametersAll() {

        val body = mockMvc.perform(MockMvcRequestBuilders.get("/laptimes"))
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val laptimes: List<Laptime> = this.objectMapper.readValue(body.contentAsString)

        assertThat(laptimes).containsExactlyInAnyOrder(*allLaptimes.toTypedArray())
    }

    @Test
    fun byParametersTrackId() {
        val track = hedemora
        val body = mockMvc.perform(MockMvcRequestBuilders.get("/laptimes?trackId=${track.id}"))
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val laptimes: List<Laptime> = this.objectMapper.readValue(body.contentAsString)

        assertThat(laptimes).containsExactlyInAnyOrder(*allLaptimes.filter { it.track.id == track.id }.toTypedArray())
    }

    @Test
    fun byParametersDriverId() {
        val driver = adam
        val body = mockMvc.perform(MockMvcRequestBuilders.get("/laptimes?driverId=${driver.id}"))
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val laptimes: List<Laptime> = this.objectMapper.readValue(body.contentAsString)

        assertThat(laptimes).containsExactlyInAnyOrder(*allLaptimes.filter { it.driver.id == driver.id }.toTypedArray())
    }

    @Test
    fun byParametersKart() {
        val kart = KZ2
        val body = mockMvc.perform(MockMvcRequestBuilders.get("/laptimes?kart=$kart"))
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val laptimes: List<Laptime> = this.objectMapper.readValue(body.contentAsString)

        assertThat(laptimes).containsExactlyInAnyOrder(*allLaptimes.filter { it.kart == kart }.toTypedArray())
    }

    @Test
    fun byParametersTrackIdAndDriverId() {
        val driver = adam
        val track = hedemora
        val body = mockMvc.perform(MockMvcRequestBuilders.get("/laptimes?driverId=${driver.id}&trackId=${track.id}"))
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val laptimes: List<Laptime> = this.objectMapper.readValue(body.contentAsString)

        assertThat(laptimes).containsExactlyInAnyOrder(*allLaptimes.filter { it.driver.id == driver.id }.filter { it.track.id == track.id }.toTypedArray())
    }

    @Test
    fun byId() {
        val laptime = laptimeAdamAmsberg4
        `when`(laptimeRepository.get(laptime.id)).thenReturn(laptime)

        val body =
            mockMvc.perform(MockMvcRequestBuilders.get("/laptimes/${laptime.id}"))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andReturn().response
        val resultLaptime = objectMapper.readValue(body.contentAsString, Laptime::class.java)
        assertThat(resultLaptime).isEqualTo(laptime)
    }

    @Test
    fun byIdNotFoundShouldReturn404() {
        val laptimeId = -1
        `when`(laptimeRepository.get(laptimeId)).thenReturn(null)

        mockMvc.perform(MockMvcRequestBuilders.get("/laptimes/$laptimeId"))
            .andExpect(status().isNotFound)
            .andExpect(content().string(""))
    }
}
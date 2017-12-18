package nu.westlin.fastestlaps.web

import com.fasterxml.jackson.module.kotlin.readValue
import nu.westlin.fastestlaps.domain.Driver
import nu.westlin.fastestlaps.domain.DriverRepository
import nu.westlin.fastestlaps.test.WebIntegrationTest
import nu.westlin.fastestlaps.test.adam
import nu.westlin.fastestlaps.test.allDrivers
import nu.westlin.fastestlaps.test.peter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(DriverController::class)
class DriverControllerTest : WebIntegrationTest() {

    @MockBean
    lateinit var repository: DriverRepository

    @Test
    fun all() {
        `when`(repository.all()).thenReturn(allDrivers)

        val body = mockMvc.perform(get("/drivers"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val drivers: List<Driver> = this.objectMapper.readValue(body.contentAsString)

        assertThat(drivers).isEqualTo(allDrivers)
    }

    @Test
    fun byId() {
        val driver = adam
        `when`(repository.get(driver.id)).thenReturn(driver)

        val body =
            mockMvc.perform(get("/drivers/${driver.id}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andReturn().response
        val resultDriver = objectMapper.readValue(body.contentAsString, Driver::class.java)
        assertThat(resultDriver).isEqualTo(driver)
    }

    @Test
    fun create() {
        val driver = peter

        mockMvc
            .perform(post("/drivers").content(objectMapper.writeValueAsString(driver)).contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().string(""))
    }

    @Test
    fun createShouldReturn406WhenDriverAlreadyExist() {
        val driver = peter

        `when`(repository.create(driver)).thenThrow(IllegalArgumentException("Driver $driver already exist"))

        mockMvc
            .perform(post("/drivers").content(objectMapper.writeValueAsString(driver)).contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotAcceptable)
            .andExpect(content().string(""))
    }

    @Test
    fun byIdNotFoundShouldReturn404() {
        val driverId = -1
        `when`(repository.get(driverId)).thenReturn(null)

        mockMvc.perform(MockMvcRequestBuilders.get("/drivers/$driverId"))
            .andExpect(status().isNotFound)
            .andExpect(content().string(""))
    }
}
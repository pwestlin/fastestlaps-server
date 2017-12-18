package nu.westlin.fastestlaps.web

import com.fasterxml.jackson.module.kotlin.readValue
import nu.westlin.fastestlaps.domain.Track
import nu.westlin.fastestlaps.domain.TrackRepository
import nu.westlin.fastestlaps.test.WebIntegrationTest
import nu.westlin.fastestlaps.test.allTracks
import nu.westlin.fastestlaps.test.hedemora
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(TrackController::class)
class TrackControllerTest : WebIntegrationTest() {

    @MockBean
    lateinit var repository: TrackRepository

    @Test
    fun all() {
        `when`(repository.all()).thenReturn(allTracks)

        val body = mockMvc.perform(MockMvcRequestBuilders.get("/tracks"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andReturn().response

        val tracks: List<Track> = this.objectMapper.readValue(body.contentAsString)

        assertThat(tracks).isEqualTo(allTracks)
    }

    @Test
    fun byId() {
        val track = hedemora
        `when`(repository.get(track.id)).thenReturn(track)

        val body =
            mockMvc.perform(MockMvcRequestBuilders.get("/tracks/${track.id}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andReturn().response
        val resultTrack = objectMapper.readValue(body.contentAsString, Track::class.java)
        assertThat(resultTrack).isEqualTo(track)
    }

    @Test
    fun byIdNotFoundShouldReturn404() {
        val trackId = -1
        `when`(repository.get(trackId)).thenReturn(null)

        mockMvc.perform(MockMvcRequestBuilders.get("/tracks/$trackId"))
            .andExpect(status().isNotFound)
            .andExpect(content().string(""))
    }

}
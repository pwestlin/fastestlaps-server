package nu.westlin.fastestlaps.web

import nu.westlin.fastestlaps.test.WebIntegrationTest
import org.junit.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(HomeController::class)
class HomeControllerTest : WebIntegrationTest() {

    @Test
    fun root() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(content().string("Hello, I am the kart server. How may I help you?"))
    }
}
package nu.westlin.fastestlaps.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import javax.inject.Inject

@RunWith(SpringRunner::class)
abstract class WebIntegrationTest {

    @Inject
    lateinit var mockMvc: MockMvc

    val objectMapper =
        ObjectMapper()
            .registerModule(JavaTimeModule())   // Needed for LocalDate
            .registerKotlinModule()
}

abstract class UnitTest {

    val objectMapper =
        ObjectMapper()
            .registerModule(JavaTimeModule())   // Needed for LocalDate
            .registerKotlinModule()


    @Before
    fun setupMockito() {
        MockitoAnnotations.initMocks(this)
    }

}
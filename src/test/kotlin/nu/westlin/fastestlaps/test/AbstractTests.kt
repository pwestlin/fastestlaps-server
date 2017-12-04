package nu.westlin.fastestlaps.test

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
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

    val objectMapper = jacksonObjectMapper()
}

abstract class UnitTest {

    @Before
    fun setupMockito() {
        MockitoAnnotations.initMocks(this)
    }

}
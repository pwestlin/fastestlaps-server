package nu.westlin.fastestlaps.web

import nu.westlin.fastestlaps.domain.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class WebExceptionHandler {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(e: IllegalArgumentException, response: HttpServletResponse) {
        logger.error("Error", e)
        response.status = HttpStatus.NOT_ACCEPTABLE.value()
    }

}

@RestController
class HomeController {

    @GetMapping("/")
    fun home() = "Hello, I am the kart server. How may I help you?"

}

@RestController
@RequestMapping("/drivers")
class DriverController(val driverRepository: DriverRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = driverRepository.all()

    @GetMapping(value = "/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int) = driverRepository.get(id)

    @PostMapping(consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun create(@RequestBody driver: Driver) = driverRepository.create(driver)

}

@RestController
@RequestMapping("/tracks")
class TrackController(val trackRepository: TrackRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = trackRepository.all()

    @GetMapping(value = "/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int) = trackRepository.get(id)

    @PostMapping(consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun create(@RequestBody track: Track) = trackRepository.create(track)
}

@RestController
@RequestMapping("/laptimes")
class LaptimeController(val laptimeRepository: LaptimeRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byParameters(@RequestParam(required = false) trackId: Int?, @RequestParam(required = false) driverId: Int?): List<Laptime> {
        // TODO petves: Insert conditions (trackId != null and driverId != null) directly into the stream, just for learning and fun :)
        // Se Java example at https://stackoverflow.com/questions/42424191/java-8-applying-stream-filter-based-on-a-condition
        var laptimes = laptimeRepository.all()
        if (trackId != null) laptimes = laptimes.filter { it.track.id == trackId }
        if (driverId != null) laptimes = laptimes.filter { it.driver.id == driverId }

        return laptimes.sortedBy { it.time }
    }

    @GetMapping(value = "/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int, response: HttpServletResponse): Laptime? {
        val laptime = laptimeRepository.get(id)
        if (laptime == null) {
            response.status = HttpStatus.NOT_FOUND.value()
        }
        return laptime
    }
}


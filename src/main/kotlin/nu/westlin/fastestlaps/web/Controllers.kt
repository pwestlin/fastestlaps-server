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
class LaptimeController(
    val trackRepository: TrackRepository,
    val laptimeRepository: LaptimeRepository,
    val driverRepository: DriverRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = trackRepository.all()

    @GetMapping(value = "/track/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun fastestByTrackId(@PathVariable("id") trackId: Int): List<Laptime> {
        val track = trackRepository.get(trackId)
        return laptimeRepository.all()
            .filter { it.track == track }
            .sortedBy { it.time }
    }

    @GetMapping(value = "/driver/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun fastestByDriverId(@PathVariable("id") driverId: Int): List<Laptime> {
        val driver = driverRepository.get(driverId)
        return laptimeRepository.all()
            .filter { it.driver == driver }
            .sortedBy { it.track.name }
            .sortedBy { it.kart }
    }

}


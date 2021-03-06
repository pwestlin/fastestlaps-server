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
    fun home() = "Hello, I am the kart laptime server. How may I help you?"

}

@RestController
@RequestMapping("/drivers")
class DriverController(val driverRepository: DriverRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = driverRepository.all().sortedBy { it.name }

    @GetMapping(value = "/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int, response: HttpServletResponse): Driver? {
        val driver = driverRepository.get(id)
        if (driver == null) {
            response.status = HttpStatus.NOT_FOUND.value()
        }
        return driver
    }

    @PostMapping(consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun create(@RequestBody driver: Driver) = driverRepository.create(driver)

}

@RestController
@RequestMapping("/tracks")
class TrackController(val trackRepository: TrackRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = trackRepository.all().sortedBy { it.name }

    @GetMapping(value = "/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int, response: HttpServletResponse): Track? {
        val track = trackRepository.get(id)
        if (track == null) {
            response.status = HttpStatus.NOT_FOUND.value()
        }
        return track
    }

    @PostMapping(consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun create(@RequestBody track: Track) = trackRepository.create(track)
}

@RestController
@RequestMapping("/laptimes")
class LaptimeController(val laptimeRepository: LaptimeRepository) {

    @GetMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byParameters(
        @RequestParam(required = false) trackId: Int?,
        @RequestParam(required = false) driverId: Int?,
        @RequestParam(required = false) kart: Kart?,
        @RequestParam(required = false) fastest: Boolean?
    ): List<Laptime> {
        var laptimes = laptimeRepository.all()
            // Read this as: apply filter only if trackId != null
            .filter { it -> if (trackId != null) it.track.id == trackId else true }
            // Read this as: apply filter only if driverId != null
            .filter { it -> if (driverId != null) it.driver.id == driverId else true }
            // Read this as: apply filter only if kart != null
            .filter { it -> if (kart != null) it.kart == kart else true }

        if (fastest == true) {
            laptimes = this.getFastestLaptimesPerClass(laptimes)
        }

        return laptimes.sortedWith(compareBy({ it.track.name }, { it.time }))
    }

    internal fun getFastestLaptimesPerClass(laptimes: List<Laptime>): List<Laptime> {
        return laptimes
            .groupBy { it.track }
            //.map { it.value.minBy { it.time } }
            .flatMap { it.value.groupBy { it.kart.name }.map { it.value.minBy { it.time } } }
            .filterNotNull()
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


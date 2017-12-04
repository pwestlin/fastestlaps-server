package nu.westlin.fastestlaps.web

import nu.westlin.fastestlaps.domain.DriverRepository
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun home() = "Hello, I am the kart server. How may I help you?"

}

@RestController
//@RequestMapping("/drivers")
class DriverController(val driverRepository: DriverRepository) {

    @GetMapping(
        value = "/drivers",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = driverRepository.all()

    @GetMapping(
        value = "/drivers/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int) = driverRepository.get(id)

}

/*
@RestController
@RequestMapping("/tracks")
class TracksController(val tracksRepository: TracksRepository) {

    @GetMapping(
        value = "/drivers",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun all() = driverRepository.all()

    @GetMapping(
        value = "/drivers/{id}",
        produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun byId(@PathVariable id: Int) = driverRepository.get(id)

}*/

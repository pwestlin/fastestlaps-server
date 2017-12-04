package nu.westlin.fastestlaps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FastestlapsApplication

fun main(args: Array<String>) {
    runApplication<FastestlapsApplication>(*args)
}

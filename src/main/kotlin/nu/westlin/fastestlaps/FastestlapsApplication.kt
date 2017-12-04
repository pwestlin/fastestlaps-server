package nu.westlin.fastestlaps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
//@SpringBootApplication(scanBasePackageClasses = arrayOf(FastestlapsApplication::class))
@ComponentScan("nu.westlin.fastestlaps")
class FastestlapsApplication

fun main(args: Array<String>) {
    runApplication<FastestlapsApplication>(*args)
}

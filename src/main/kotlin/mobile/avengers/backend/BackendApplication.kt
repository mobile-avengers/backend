package mobile.avengers.backend

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableConfigurationProperties
class BackendApplication : CommandLineRunner {
    @Value("\${server.port}")
    lateinit var port: String

    override fun run(vararg args: String) {
        println("Server started at $port port")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(BackendApplication::class.java, *args)
}

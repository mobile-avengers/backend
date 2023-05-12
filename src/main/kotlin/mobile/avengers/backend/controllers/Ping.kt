package mobile.avengers.backend.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Ping {
    @GetMapping("/ping", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun ping(): String {
        return "{\"status\": \"ok\"}"
    }
}

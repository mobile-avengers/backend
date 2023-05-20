package mobile.avengers.backend.handlers

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class NotFoundHandler {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleException(): ResponseEntity.HeadersBuilder<*> {
        return ResponseEntity.notFound()
    }
}
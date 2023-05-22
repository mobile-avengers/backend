package mobile.avengers.backend.handlers

import jakarta.persistence.EntityNotFoundException
import mobile.avengers.backend.exceptions.ConditionGettingException
import mobile.avengers.backend.exceptions.OrderNotFoundException
import mobile.avengers.backend.exceptions.ProductNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class NotFoundHandler {
    @ExceptionHandler(
        EntityNotFoundException::class,
        ProductNotFoundException::class,
        OrderNotFoundException::class,
        ConditionGettingException::class
    )
    fun handleException(): ResponseEntity.HeadersBuilder<*> {
        return ResponseEntity.notFound()
    }
}
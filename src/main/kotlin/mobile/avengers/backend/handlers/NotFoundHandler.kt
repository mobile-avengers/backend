package mobile.avengers.backend.handlers

import jakarta.persistence.EntityNotFoundException
import mobile.avengers.backend.exceptions.NoSuchConditionException
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
    )
    fun handle404Exception(): ResponseEntity.HeadersBuilder<*> {
        return ResponseEntity.notFound()
    }

    @ExceptionHandler(
        NoSuchConditionException::class,
    )
    fun handle400Exception(): ResponseEntity.HeadersBuilder<*> {
        return ResponseEntity.badRequest()
    }
}
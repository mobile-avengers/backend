package mobile.avengers.backend.controllers

import mobile.avengers.backend.entities.Order
import mobile.avengers.backend.services.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController (
    private val orderService: OrderService
) {

    @GetMapping("/api/v1/orders")
    fun getAllOrders() : List<Order> {
        return orderService.getAllOrders()
    }


}
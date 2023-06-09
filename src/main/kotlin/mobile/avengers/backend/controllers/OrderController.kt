package mobile.avengers.backend.controllers

import io.swagger.v3.oas.annotations.Operation
import mobile.avengers.backend.entities.Order
import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.models.CreateOrderRequest
import mobile.avengers.backend.services.OrderService
import org.springframework.web.bind.annotation.*

@RestController
class OrderController (
    private val orderService: OrderService
) {
    @GetMapping("/api/v1/orders")
    fun getAllOrders() : List<Order> {
        return orderService.getAllOrders()
    }

    @GetMapping("/api/v1/productsFromOrder")
    fun getProductsFromOrder(@RequestParam orderId: Long) : List<Product> {
        return orderService.getProductsFromOrder(orderId)
    }

    @PostMapping("/api/v1/order")
    fun createOrder(
        @RequestParam userId: Long,
        @RequestBody order: CreateOrderRequest
    ): Order {
        return orderService.createOrder(userId, order)
    }

    @Operation(summary = "Получение заказа по id, привилегия админа")
    @GetMapping("/api/v1/order")
    fun getCurrentOrderByOrderId(@RequestParam orderId: Long) : Order {
        return orderService.getCurrentOrderById(orderId)
    }

    @Operation(summary = "Изменить состояние заказа, привилегия админа")
    @PutMapping("/api/v1/order")
    fun changeOrderConditionById(@RequestParam orderId: Long, @RequestParam newStatus: String) : Order {
        return orderService.changeOrderConditionById(orderId, newStatus)
    }


}

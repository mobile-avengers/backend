package mobile.avengers.backend.services

import mobile.avengers.backend.entities.Order
import mobile.avengers.backend.repositories.OrderProductRepository
import mobile.avengers.backend.repositories.OrderRepository
import mobile.avengers.backend.repositories.ProductRepository
import mobile.avengers.backend.repositories.UserRepository
import org.springframework.stereotype.Service


@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val orderProductRepository: OrderProductRepository,
    private val productRepository: ProductRepository
) {


    fun getAllOrders() : List<Order> {
        return orderRepository.findAll()
    }



}
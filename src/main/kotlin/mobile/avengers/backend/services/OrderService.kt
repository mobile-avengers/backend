package mobile.avengers.backend.services

import mobile.avengers.backend.entities.*
import mobile.avengers.backend.enums.ProductConditions
import mobile.avengers.backend.exceptions.OrderNotFoundException
import mobile.avengers.backend.exceptions.ProductNotFoundException
import mobile.avengers.backend.models.CreateOrderRequest
import mobile.avengers.backend.repositories.*
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime


@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val orderProductRepository: OrderProductRepository,
    private val productRepository: ProductRepository,
) {
    fun getAllOrders() : List<Order> {
        return orderRepository.findAll()
    }

    fun getProductsFromOrder(orderId: Long) : List<OrderProduct> {
        val order = orderRepository.findById(orderId)
        if(order.isEmpty){
            throw OrderNotFoundException("order with id $orderId not found")
        }
        return order.get().productsInOrder.toList()
    }

    fun createOrder(userId: Long, orderRequest: CreateOrderRequest): Order {
        val user: User = userRepository.getReferenceById(userId)
        val newOrder = Order(
            -1,
            ProductConditions.NEW.status,
            Timestamp.valueOf(LocalDateTime.now()),
            user,
        )
        val savedOrder: Order = orderRepository.save(newOrder)

        for (productId in orderRequest.productIds) {
            val product = productRepository.findById(productId)
            if (product.isEmpty) {
                throw ProductNotFoundException("product with id $productId not found")
            }
            product.get().inOrder = true
            productRepository.save(product.get())

            val orderProduct = OrderProduct(
                -1,
                ProductConditions.NEW.status,
                product.get(),
                savedOrder,
            )
            orderProductRepository.save(orderProduct)
        }
        return savedOrder
    }
}

package mobile.avengers.backend.services

import mobile.avengers.backend.entities.*
import mobile.avengers.backend.enums.OrderConditions
import mobile.avengers.backend.enums.ProductConditions
import mobile.avengers.backend.exceptions.ConditionGettingException
import mobile.avengers.backend.exceptions.OrderNotFoundException
import mobile.avengers.backend.exceptions.ProductNotFoundException
import mobile.avengers.backend.models.CreateOrderRequest
import mobile.avengers.backend.repositories.*
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Optional


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

    fun getProductsFromOrder(orderId: Long) : List<Product> {
        val order = orderRepository.findById(orderId)
        if(order.isEmpty){
            throw OrderNotFoundException("order with id $orderId not found")
        }
        val poducts = mutableListOf<Product>()
        for(product in order.get().productsInOrder.toList()) {
            poducts.add(product.product)
        }
        return poducts  // TODO добавить поле condition, возвращать объединённое значение Product и OrderProduct в формате, который принимает мобильное приложение (см. LotModel)
    }

    fun createOrder(userId: Long, orderRequest: CreateOrderRequest): Order {
        val user: User = userRepository.getReferenceById(userId)
        val newOrder = Order(
            -1,
            ProductConditions.NEW.status,
            Timestamp.valueOf(LocalDateTime.now()),
            user,
        )
        var savedOrder: Order = orderRepository.save(newOrder)

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
            savedOrder.cost += product.get().price
            orderProductRepository.save(orderProduct)
        }
        return orderRepository.save(savedOrder)
    }

    fun changeOrderConditionById(orderId: Long, newStatus: String): Order {
        try {
            val condition: OrderConditions = OrderConditions.valueOf(newStatus)
            var order: Order = getCurrentOrderById(orderId)

            order.condition = condition.toString()
            return orderRepository.save(order)
        } catch (e: IllegalArgumentException) {
            throw ConditionGettingException("$newStatus isn't real order status")
        }
    }

    fun getCurrentOrderById(orderId: Long) : Order {
        val orderOpt: Optional<Order> = orderRepository.findById(orderId)
        if (orderOpt.isEmpty) {
            throw OrderNotFoundException("order with id $orderId not found")
        }
        return orderOpt.get()
    }

}

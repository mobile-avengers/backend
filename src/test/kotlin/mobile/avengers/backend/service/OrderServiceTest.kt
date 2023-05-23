package mobile.avengers.backend.service

import mobile.avengers.backend.entities.*
import mobile.avengers.backend.models.CreateOrderRequest
import mobile.avengers.backend.repositories.*
import mobile.avengers.backend.services.OrderService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.sql.Timestamp

@SpringBootTest
@DisplayName("Тест OrderService")
@ExtendWith(SpringExtension::class)
class OrderServiceTest {

    // Service
    @Autowired
    lateinit var orderService: OrderService

    // Repository
    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productTypeRepository: ProductTypeRepository

    @Autowired
    lateinit var orderProductRepository: OrderProductRepository

    @Nested
    @DisplayName("Позитивные сценарии")
    inner class OrderServiceTest_positive {
        @Test
        @DisplayName("Получить список продкутов заказа")
        fun getProductsFromOrder_getProductsFromOrder() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val productType = ProductType(0, "type")
            val product1Given = Product(1, "name1", 1F, "link1", "size1", "color1", productType, user, false)
            val product2Given = Product(2, "name2", 2F, "lonk2", "size2", "color2", productType, user, false)

            val order = Order(0, "condition", Timestamp(0, 0, 0, 0, 0, 0, 0), user, mutableListOf(), 0F)

            val orderProduct1Given = OrderProduct(1, "condition1", product1Given, order)
            val orderProduct2Given = OrderProduct(2, "condition2", product2Given, order)

            roleRepository.save(role)
            userRepository.save(user)
            productTypeRepository.save(productType)
            productRepository.saveAll(listOf(product1Given, product2Given))
            orderRepository.save(order)
            orderProductRepository.saveAll(listOf(orderProduct1Given, orderProduct2Given))

            // when
            val productsFromOrderList = orderService.getProductsFromOrder(order.id)

            // then
            assertEquals(productsFromOrderList.size, 2, "Получено некрректное количество Product")
            assertEquals(productsFromOrderList.find { e -> e == product1Given }, product1Given, "Ошбика тестового объекта Product1")
            assertEquals(productsFromOrderList.find { e -> e == product2Given }, product2Given, "Ошбика тестового объекта Product2")
        }
        @Test
        @DisplayName("Создание заказа")
        fun createOrder_createOrder() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val productType = ProductType(0, "type")
            val product1Given = Product(1, "name1", 1F, "link1", "size1", "color1", productType, user, false)
            val product2Given = Product(2, "name2", 2F, "lonk2", "size2", "color2", productType, user, false)

            val orderRequest = CreateOrderRequest(listOf(product1Given.id, product2Given.id))

            roleRepository.save(role)
            userRepository.save(user)
            productTypeRepository.save(productType)
            productRepository.saveAll(listOf(product1Given, product2Given))

            // when
            val order = orderService.createOrder(user.id, orderRequest)

            // then
            assertEquals(order.user, user, "Пользователь в заказе указан некорректно")
            assertEquals(order.productsInOrder.size, 2, "Количество товаров в заказе некорректно")
            assertEquals(order.productsInOrder.find { e -> e.product == product1Given}?.product, product1Given, "Ошибка тестового объекта Product1")
            assertEquals(order.productsInOrder.find { e -> e.product == product2Given}?.product, product2Given, "Ошибка тестового объекта Product2")
            assertEquals(order.condition, "new", "Статус заказа некорректен")
            assertEquals(order.cost, 3F, "Общая стоимость заказа некорректна")
        }

        @Test
        @DisplayName("Сменить состояние заказа")
        fun changeOrderConditionById_changeOrderCondition() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val order = Order(0, "condition", Timestamp(0, 0, 0, 0, 0, 0, 0), user, mutableListOf(), 0F)

            val newCondition = "sent"

            roleRepository.save(role)
            userRepository.save(user)
            orderRepository.save(order)

            // when
            val newOrder = orderService.changeOrderConditionById(order.id, newCondition)

            // then
            assertEquals(newOrder, newCondition, "Ошибка изменения состояния заказа")
        }

        @Test
        @DisplayName("Получить заказ")
        fun getCurrentOrderById_getCurrentOrder() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val order = Order(0, "condition", Timestamp(0, 0, 0, 0, 0, 0, 0), user, mutableListOf(), 0F)

            roleRepository.save(role)
            userRepository.save(user)
            orderRepository.save(order)

            // when
            val orderFromDB = orderService.getCurrentOrderById(order.id)

            // then
            assertEquals(order, orderFromDB, "Ошибка корректности Order")
        }
    }

    @Nested
    @DisplayName("Негативные сценарии")
    inner class OrderServiceTest_negative {

    }
}
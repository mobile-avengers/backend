package mobile.avengers.backend.service

import mobile.avengers.backend.entities.*
import mobile.avengers.backend.repositories.*
import mobile.avengers.backend.services.ProductService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@DisplayName("Тест ProductService")
@ExtendWith(SpringExtension::class)
class ProductServiceTest {

    // Service
    @Autowired
    lateinit var productService: ProductService


    // Repository
    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productTypeRepository: ProductTypeRepository

    @BeforeAll
    fun clearDataBase() {
        productRepository.deleteAll()
        productTypeRepository.deleteAll()
        userRepository.deleteAll()
        roleRepository.deleteAll()
    }
    @Nested
    @DisplayName("Позитивные сценарии")
    inner class ProductServiceTest_positive {
        @Test
        @DisplayName("Получение карт пользователя")
        fun getUserProductsInCart_getProductsOfUser() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val productType = ProductType(0, "type")
            val product1Given = Product(1, "name1", 1F, "link1", "size1", "color1", productType, user, false)
            val product2Given = Product(2, "name2", 2F, "lonk2", "size2", "color2", productType, user, false)

            roleRepository.save(role)
            userRepository.save(user)
            productTypeRepository.save(productType)
            productRepository.saveAll(listOf(product1Given, product2Given))

            //when
            val userProductsList = productService.getUserProductsInCart(user.id)

            // then
            assertEquals(userProductsList.size, 2, "Получено некрректное количество Product")
            assertEquals(userProductsList.find { e -> e == product1Given }, product1Given, "Ошбика тестового объекта Product1")
            assertEquals(userProductsList.find { e -> e == product2Given }, product2Given, "Ошбика тестового объекта Product2")
        }

        @Test
        @DisplayName("Добавить продукт для пользователя")
        fun addProductToCart_addProductForUser() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val productType = ProductType(0, "type")
            val product1Given = Product(1, "name1", 1F, "link1", "size1", "color1", productType, null, false)

            roleRepository.save(role)
            userRepository.save(user)
            productTypeRepository.save(productType)
            productRepository.saveAll(listOf(product1Given))

            //when
            val savedProduct = productService.addProductToCart(product1Given, user.id)

            // then
            assertEquals(productRepository.findAllByUserAndInOrder(user, false).size, 1, "Получено некрректное количество Product")
            assertEquals(productRepository.findAllByUserAndInOrder(user, false)[0], savedProduct, "Product сохранился некорректно")
        }

        @Test
        @DisplayName("Получить список всех продуктов")
        fun getAllProducts_getAllProducts() {
            // given
            val role = Role(0, "role")
            val user = User(0, "mail", "password", role, mutableListOf())

            val productType = ProductType(0, "type")
            val product1Given = Product(1, "name1", 1F, "link1", "size1", "color1", productType, user, false)
            val product2Given = Product(2, "name2", 2F, "link2", "size2", "color2", productType, user, false)

            roleRepository.save(role)
            userRepository.save(user)
            productTypeRepository.save(productType)
            productRepository.saveAll(listOf(product1Given, product2Given))

            //when
            val allProductsList = productService.getAllProducts()

            // then
            assertEquals(allProductsList.size, 2, "Получено некрректное количество Product")
            assertEquals(allProductsList.find { e -> e == product1Given }, product1Given, "Ошбика тестового объекта Product1")
            assertEquals(allProductsList.find { e -> e == product2Given }, product2Given, "Ошбика тестового объекта Product2")
        }
    }

    @Nested
    @DisplayName("Негативные сценарии")
    inner class ProductServiceTest_negative {
    }
}
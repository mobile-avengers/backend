package mobile.avengers.backend.services

import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.entities.User
import mobile.avengers.backend.repositories.ProductRepository
import mobile.avengers.backend.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductService (
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {

    fun getUserProductsInCart(userId: Long) : List<Product> {
        val user: User = userRepository.getReferenceById(userId)
        return productRepository.findAllByUserAndInOrder(user, false)
    }

    fun addProductToCart(product: Product, userId: Long) : ResponseEntity<Product> {
        val user: User = userRepository.getReferenceById(userId)
        product.user = user
        val savedProduct: Product = productRepository.save(product)
        return ResponseEntity.ok(savedProduct)
    }

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }
}

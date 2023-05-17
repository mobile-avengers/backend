package mobile.avengers.backend.services

import mobile.avengers.backend.entities.Cart
import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.entities.User
import mobile.avengers.backend.repositories.CartRepository
import mobile.avengers.backend.repositories.ProductRepository
import mobile.avengers.backend.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class CartService (
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) {

    fun getUserCarts(userId: Long) : List<Cart> {
        val user: User = userRepository.getReferenceById(userId)
        return cartRepository.findAllByUser(user)
    }

    fun addProduct(product: Product, userId: Long) : ResponseEntity<Product> {
        val savedProduct: Product = productRepository.save(product)
        val user: User = userRepository.getReferenceById(userId)
        val cart: Cart = Cart(null, null, user, product)
        cartRepository.save(cart)
        return ResponseEntity.ok(savedProduct)
    }

}
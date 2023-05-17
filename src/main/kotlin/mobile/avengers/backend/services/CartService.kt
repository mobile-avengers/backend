package mobile.avengers.backend.services

import mobile.avengers.backend.entities.Cart
import mobile.avengers.backend.entities.User
import mobile.avengers.backend.repositories.CartRepository
import mobile.avengers.backend.repositories.UserRepository
import org.springframework.stereotype.Service


@Service
class CartService (
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository
) {

    fun getUserCarts(userId: Long) : List<Cart> {
        val user: User = userRepository.getReferenceById(userId)
        return cartRepository.findAllByUser(user)
    }

}
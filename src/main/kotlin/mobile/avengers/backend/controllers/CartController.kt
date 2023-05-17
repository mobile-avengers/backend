package mobile.avengers.backend.controllers

import mobile.avengers.backend.entities.Cart
import mobile.avengers.backend.services.CartService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CartController (
    private val cartService: CartService
) {

    @GetMapping("/api/v1/cart")
    fun getUserCarts(userId: Long): List<Cart> {
        return cartService.getUserCarts(userId)
    }

}
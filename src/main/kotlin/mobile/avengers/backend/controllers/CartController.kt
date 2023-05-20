package mobile.avengers.backend.controllers

import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class CartController (
    private val productService: ProductService
) {
    @GetMapping("/api/v1/cart")
    fun getUserProductsInCart(@RequestParam userId: Long): List<Product> {
        return productService.getUserProductsInCart(userId)
    }

    @PostMapping("/api/v1/cart")
    fun addProductToUserCart(
        @RequestBody product: Product,
        @RequestParam userId: Long
    ) : ResponseEntity<Product> {
        return productService.addProductToCart(product, userId);
    }
}

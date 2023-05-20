package mobile.avengers.backend.controllers

import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.services.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController (
    private val productService: ProductService
) {
    @GetMapping("/products/getAll")
    fun getAllProducts(): List<Product> {
        return productService.getAllProducts()
    }
}

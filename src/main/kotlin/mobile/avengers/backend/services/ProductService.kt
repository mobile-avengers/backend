package mobile.avengers.backend.services

import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

}

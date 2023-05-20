package mobile.avengers.backend.repositories

import mobile.avengers.backend.entities.Product
import mobile.avengers.backend.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByUserAndInOrder(user: User, inOrder: Boolean): List<Product>
}

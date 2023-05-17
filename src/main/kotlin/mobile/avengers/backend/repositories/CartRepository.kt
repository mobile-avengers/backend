package mobile.avengers.backend.repositories

import mobile.avengers.backend.entities.Cart
import mobile.avengers.backend.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart, Long> {
    fun findAllByUser(user: User): List<Cart>

}

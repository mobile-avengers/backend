package mobile.avengers.backend.repositories

import mobile.avengers.backend.entities.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderProductRepository : JpaRepository<OrderProduct, Long> {
}
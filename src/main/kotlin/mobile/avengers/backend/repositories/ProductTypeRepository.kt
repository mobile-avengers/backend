package mobile.avengers.backend.repositories

import mobile.avengers.backend.entities.ProductType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductTypeRepository : JpaRepository<ProductType, Long> {
}
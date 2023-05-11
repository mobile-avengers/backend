package mobile.avengers.backend.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.management.relation.Role

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
}
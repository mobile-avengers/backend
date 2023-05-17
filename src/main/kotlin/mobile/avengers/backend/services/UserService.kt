package mobile.avengers.backend.services

import mobile.avengers.backend.entities.User
import mobile.avengers.backend.repositories.RoleRepository
import mobile.avengers.backend.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository
) {
    fun getAllUsers() : List<User> {
        return userRepository.findAll()
    }


}






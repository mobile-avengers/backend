package mobile.avengers.backend.service

import mobile.avengers.backend.entities.Role
import mobile.avengers.backend.entities.User
import mobile.avengers.backend.repositories.RoleRepository
import mobile.avengers.backend.repositories.UserRepository
import mobile.avengers.backend.services.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@DisplayName("Тест UserService")
@ExtendWith(SpringExtension::class)
class UserServiceTest {
    // Service
    @Autowired
    lateinit var userService: UserService

    // Repository
    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Nested
    @DisplayName("Позитивные сценарии")
    inner class UserServiceTest_positive {
        @Test
        @DisplayName("Получить список всех пользователей")
        fun getAllUsers_getAllUsers() {
            // given
            val role = Role(0, "USER")

            val user1Given = User(0, "mail", "password", role, mutableListOf())
            val user2Given = User(0, "mail", "password", role, mutableListOf())

            roleRepository.save(role)
            userRepository.saveAll(listOf(user1Given, user2Given))

            // when
            val usersList = userService.getAllUsers()

            // then

            assertEquals(usersList.size, 2, "Получено некрректное количество User")
            assertEquals(usersList.find { e -> e == user1Given }, user1Given, "Ошбика тестового объекта User1")
            assertEquals(usersList.find { e -> e == user2Given }, user2Given, "Ошбика тестового объекта User2")
        }
    }
}
package mobile.avengers.backend.controllers

import mobile.avengers.backend.entities.User
import mobile.avengers.backend.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping("/users/getAll")
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }
}

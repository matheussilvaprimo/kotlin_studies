package com.myShop.users.controllers

import com.myShop.users.models.UserDTO
import com.myShop.users.services.UserService
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
class UsersController(private val userService: UserService) {
	@GetMapping
	fun index() = "What up my homies";

	@GetMapping("/name/{name}")
	fun getByName(@PathVariable name: String): UserDTO? = userService.getUserByName(name)

	@GetMapping("/id/{id}")
	fun getById(@PathVariable id: String): UserDTO? = userService.getUserByUUID(ObjectId(id))

	@PostMapping
	fun post(@RequestBody dto: UserDTO): String? = userService.saveUser(dto)
}

package com.myShop.users.services

import com.myShop.users.domain.User
import com.myShop.users.models.UserDTO
import com.myShop.users.repository.IUserRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Example
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: IUserRepository): IUserService {
    override fun saveUser(user: UserDTO): String? {
        val entity = User()
        entity.fullName = user.name
        entity.address = user.address
        entity.dateOfBirth = user.birthDate

        return userRepository.insert(entity).id.toString()
    }

    override fun getAllUsers(): List<UserDTO> {
        val users = userRepository.findAll()
        return users.map { UserDTO(it.id.toString(), it.fullName, it.address, it.dateOfBirth) }
    }

    override fun getUserByName(name: String): UserDTO? {
        val user = User()
        user.fullName = name

        val example = Example.of(user, UntypedExampleMatcher.matchingAny())
        val entities = userRepository.findAll(example)

        if(entities.count() > 0)
        {
            val entity = entities.first { it.fullName == name }
            return UserDTO(entity.id.toString(), entity.fullName, entity.address, entity.dateOfBirth)
        }
        return null
    }

    override fun getUserByUUID(id: ObjectId): UserDTO? {
        val entity = userRepository.findById(id)

        if(entity.isPresent)
            return entity.map { UserDTO(it.id.toString(), it.fullName, it.address, it.dateOfBirth) }.get()
        return null
    }
}

interface IUserService {
    fun saveUser(user: UserDTO): String?
    fun getAllUsers(): List<UserDTO>
    fun getUserByName(name: String): UserDTO?
    fun getUserByUUID(id: ObjectId): UserDTO?
}
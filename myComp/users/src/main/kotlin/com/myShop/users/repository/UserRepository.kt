package com.myShop.users.repository

import com.myShop.users.domain.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface IUserRepository : MongoRepository<User, ObjectId>


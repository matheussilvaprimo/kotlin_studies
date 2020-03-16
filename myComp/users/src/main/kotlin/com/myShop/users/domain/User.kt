package com.myShop.users.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class User(@Id var id: ObjectId? = null,
                var fullName: String,
                var address: String,
                var dateOfBirth: LocalDate? = null,
                var creationDate: LocalDate? = null) {
    constructor() : this(null, "", "", null, LocalDate.now())
}

package com.myShop.users.models

import org.springframework.data.annotation.Id
import java.time.LocalDate

class UserDTO(@Id val id: String? = null, val name:String, val address:String, val birthDate: LocalDate?)
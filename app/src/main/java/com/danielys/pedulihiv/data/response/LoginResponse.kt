package com.danielys.pedulihiv.data.response

data class LoginResponse(
	val message: String? = null,
	val user: User? = null
)

data class User(
	val password: String? = null,
	val bodyHeight: Int? = null,
	val profilePhoto: String? = null,
	val sex: String? = null,
	val name: String? = null,
	val bodyWeight: Int? = null,
	val dateConsul: Int? = null,
	val email: String? = null,
	val age: Int? = null,
	val username: String? = null
)


package com.danielys.pedulihiv.data.response

data class LoginResponse(
	val message: String? = null,
	val user: User? = null
)

data class User(
	val password: String? = null,
	val bodyHeight: Int? = null,
	var profilePhoto: String? = null,
	val sex: String? = null,
	var name: String? = null,
	val bodyWeight: Int? = null,
	val dateConsul: Int? = null,
	val email: String? = null,
	val age: Int? = null,
	var username: String? = null
)


package com.danielys.pedulihiv.data.response

data class GetDoctorResponse(
	val data: List<DataItemDoctor?>? = null,
	val message: String? = null
)

data class DataItemDoctor(
	val profile_photo: String? = null,
	val specialist: String? = null,
	val price: Int? = null,
	val name: String? = null,
	val username: String? = null
)


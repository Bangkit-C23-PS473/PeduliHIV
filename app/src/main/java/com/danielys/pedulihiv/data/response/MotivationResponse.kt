package com.danielys.pedulihiv.data.response

data class MotivationrResponse(
	val data: Data? = null,
	val message: String? = null
)

data class Data(
	val photo: String? = null,
	val id: Int? = null,
	val text: String? = null
)

